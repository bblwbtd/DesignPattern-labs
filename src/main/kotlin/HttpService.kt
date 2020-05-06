import com.google.gson.Gson
import config.getHttpServiceConfig
import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import routers.mountLab1Router

val config = getHttpServiceConfig()
val server = embeddedServer(Netty, config.port, config.host) {
    routing {
        route("/api") {
            mountLab1Router()

        }
    }
}

val gson = Gson()

suspend fun ApplicationCall.respondJson(data: Any) {
    respondText(gson.toJson(data), ContentType.parse("application/json"))
}

suspend fun ApplicationCall.respondError(error: Error) {
    respondJson(error)
}

suspend fun <T> ApplicationCall.bodyAsJson(t: Class<T>, handler: suspend (T) -> Unit) {
    val receiveText = receiveText()
    handler(gson.fromJson(receiveText, t))
}