import com.google.gson.Gson
import config.getHttpServiceConfig
import io.ktor.application.ApplicationCall
import io.ktor.application.install
import io.ktor.http.ContentType
import io.ktor.http.content.default
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.http.content.staticRootFolder
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.websocket.WebSockets
import routers.mountLab1Router
import routers.mountLab2Router
import routers.mountLab3Router
import java.io.File

val config = getHttpServiceConfig()
val server = embeddedServer(Netty, config.port, config.host) {
    install(Sessions) {
        cookie<Session>("huaq")
    }
    install(WebSockets)
    routing {
        static {
            staticRootFolder = File("dist")
            default("index.html")
        }

        static("css") {
            staticRootFolder = File("dist")
            files("css")
        }

        static("js") {
            staticRootFolder = File("dist")
            files("js")
        }

        static("fonts") {
            staticRootFolder = File("dist")
            files("fonts")
        }

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