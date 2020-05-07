package routers

import Session
import SimulateControllerProxy
import bodyAsJson
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
import io.ktor.sessions.set
import io.ktor.websocket.WebSocketServerSession
import io.ktor.websocket.webSocket
import lab3.SimulateElevator
import lab3.bean.PressFloorButtonRequest
import lab3.bean.SimulatorConfig
import respondSuccess
import java.util.*
import kotlin.collections.HashMap

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
    }
}

val elevatorStorage = HashMap<String, SimulateElevator>()

fun getElevator(call: ApplicationCall, config: SimulatorConfig = SimulatorConfig()): SimulateElevator {
    var session = call.sessions.get<Session>()
    if (session == null) {
        session = Session(UUID.randomUUID().toString())
        call.sessions.set(session)
        return createElevator(call, config)
    }
    return elevatorStorage[session.id]!!
}

fun createElevator(call: ApplicationCall, config: SimulatorConfig): SimulateElevator {
    val session = call.sessions.get<Session>()
    return SimulateElevator(config).apply {
        controller = SimulateControllerProxy(elevatorMotor, doorMotor, config)
        elevatorStorage[session!!.id] = this
    }
}

val websocketConnection = HashMap<String, WebSocketServerSession>()

suspend fun initialWebSocket(session: WebSocketServerSession) {
    session.apply {
        call.sessions.getOrSet {
            Session(UUID.randomUUID().toString())
        }.let {
            websocketConnection[it.id]?.close()
            websocketConnection[it.id] = this
            val elevator = getElevator(call)
            val proxy = elevator.controller as SimulateControllerProxy
            try {
                for (data in proxy.channel) {
                    outgoing.send(Frame.Text(data.name))
                }
            } catch (e: Exception) {
                websocketConnection.remove(it.id)
            }

        }
    }
}