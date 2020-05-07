import com.google.gson.Gson
import config.getHttpServiceConfig
import io.ktor.application.ApplicationCall
import io.ktor.application.install
import io.ktor.http.ContentType
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import routers.mountLab1Router
import routers.mountLab2Router
import routers.mountLab3Router

val config = getHttpServiceConfig()
val server = embeddedServer(Netty, config.port, config.host) {
    install(Sessions) {
        cookie<Session>("huaq")
    }
    routing {
        route("/api") {
            mountLab1Router()
            mountLab2Router()
            mountLab3Router()
        }
    }
}

val gson = Gson()

suspend fun ApplicationCall.respondJson(data: Any? = null, code: Int = 0) {
    val s = gson.toJson(Response(data, code))
    respondText(s, ContentType.parse("application/json"))
}

suspend fun ApplicationCall.respondSuccess() {
    respondJson()
}

suspend fun ApplicationCall.respondError(customError: CustomError) {
    respondJson(customError)
}

suspend fun <T> ApplicationCall.bodyAsJson(t: Class<T>, handler: suspend (T) -> Unit) {
    val receiveText = receiveText()
    handler(gson.fromJson(receiveText, t))
}