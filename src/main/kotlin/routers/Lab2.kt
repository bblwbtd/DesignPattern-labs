package routers

import CustomError
import Session
import bodyAsJson
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import lab2.DB
import lab2.LogDBDecorator
import lab2.bean.CreateTableRequest
import lab2.bean.InsertRowRequest
import lab2.bean.JoinRowRequest
import lab2.bean.SelectRowRequest
import respondError
import respondJson
import respondSuccess
import java.util.*
import kotlin.collections.HashMap


fun Route.mountLab2Router() {
    route("/lab2") {
        get("/logs") {
            val decoratorInstance = getDecoratorInstance(call)
            val logs = decoratorInstance.logs
            call.respondJson(logs)
        }

        post("/create") {
            call.bodyAsJson(CreateTableRequest::class.java) {
                getDecoratorInstance(call).apply {
                    createTable(it.table_name, it.col)
                    call.respondSuccess()
                }
            }
        }

        post("/insert") {
            call.bodyAsJson(InsertRowRequest::class.java) {
                getDecoratorInstance(call).apply {
                    insert(it.table_name, it.data)
                    call.respondSuccess()
                }
            }
        }

        post("/select") {
            call.bodyAsJson(SelectRowRequest::class.java) {
                getDecoratorInstance(call).apply {
                    select(it.table_name, it.col, it.select)
                    call.respondSuccess()
                }
            }
        }

        post("/join") {
            call.bodyAsJson(JoinRowRequest::class.java) {
                getDecoratorInstance(call).apply {
                    try {
                        join(it.tab1_name, it.tab2_name, it.tab1_col, it.tab2_col)
                    } catch (e: Exception) {
                        call.respondError(CustomError.UnknownError)
                    }
                    call.respondSuccess()
                }
            }
        }

        post("/logs/clear") {
            getDecoratorInstance(call).apply {
                clearLogs()
                call.respondSuccess()
            }
        }
    }
}

val decoratorStorage = HashMap<String, LogDBDecorator>()

fun getDecoratorInstance(call: ApplicationCall): LogDBDecorator {
    var session = call.sessions.get<Session>()
    if (session == null) {
        session = Session(UUID.randomUUID().toString())
        call.sessions.set(session)
    }
    return decoratorStorage.getOrPut(session.id) { LogDBDecorator(DB()) }
}