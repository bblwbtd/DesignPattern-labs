package routers

import Session
import bodyAsJson
import gson
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.get
import io.ktor.sessions.getOrSet
import io.ktor.sessions.sessions
import io.ktor.websocket.WebSocketServerSession
import io.ktor.websocket.webSocket
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import lab3.SimulateElevator
import lab3.bean.PressFloorButtonRequest
import lab3.bean.SimulatorConfig
import lab3.events.ElevatorEvent
import respondSuccess
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

fun Route.mountLab3Router() {
    route("/lab3") {

        webSocket("/ws") {
            initialWebSocket(this)
        }

        post("/init") {
            call.bodyAsJson(SimulatorConfig::class.java) {
                createElevator(call, it)
                call.respondSuccess()
            }
        }

        post("/floor_button/press") {
            call.bodyAsJson(PressFloorButtonRequest::class.java) {
                getElevator(call).apply {
                    pressFloorButton(it.floor)
                    call.respondSuccess()
                }
            }
        }

        post("/close") {
            getElevator(call).apply {
                pressCloseDoorButton()
                call.respondSuccess()
            }
        }

        post("/open") {
            getElevator(call).apply {
                pressOpenDoorButton()
                call.respondSuccess()
            }
        }

        post("/block") {
            getElevator(call).apply {
                simulateDoorBlocked()
                call.respondSuccess()
            }
        }
    }
}

val elevatorStorage = HashMap<String, SimulateElevator>()

fun getElevator(call: ApplicationCall, config: SimulatorConfig = SimulatorConfig()): SimulateElevator {
    var session = call.sessions.get<Session>()
    return elevatorStorage[session!!.id]!!
}

fun createElevator(call: ApplicationCall, config: SimulatorConfig): SimulateElevator {
    var session = call.sessions.get<Session>()
    if (session == null){
        session = Session(UUID.randomUUID().toString())
        call.sessions.set("huaq", session)
    }
    return SimulateElevator(config).apply {
        elevatorStorage[session.id] = this
    }
}

val websocketConnection = HashMap<String, WebSocketServerSession>()

suspend fun initialWebSocket(session: WebSocketServerSession) {
    session.apply {
        call.sessions.getOrSet {
            Session(UUID.randomUUID().toString())
        }.let { session ->
            websocketConnection[session.id]?.close()
            websocketConnection[session.id] = this
            val elevator = getElevator(call)
            outgoing.send(Frame.Text("233"))
            val channel = Channel<ElevatorEvent>(10)
            elevator.addListener {
                launch {
                    channel.send(it)
                }
            }
            for (data in channel) {
                outgoing.send(Frame.Text(gson.toJson(data)))
            }
        }
    }
}