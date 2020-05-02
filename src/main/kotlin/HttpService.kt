import config.HttpServiceConfig
import io.vertx.reactivex.core.http.HttpServer
import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.handler.StaticHandler
import routers.Lab1Router

val server: HttpServer = vertx.createHttpServer()
val apiRouter: Router = Router.router(vertx).apply {
    mountSubRouter("/lab1", Lab1Router)
}

val router: Router = Router.router(vertx).apply {
    errorHandler(500) {
        println(it.failure().localizedMessage)
    }
    route("/*").handler(StaticHandler.create("static"))
    mountSubRouter("/api", apiRouter)
}

fun startUpHttpService(config: HttpServiceConfig) {
    server.requestHandler(router).listen(config.port, config.host) {
        if (it.succeeded()) {
            println("""Server start up successfully! Open http://${config.host}:${config.port} in your browser to start.
            """.trimMargin())
        } else {
            println("""Error occur while trying to start the server. Cause: ${it.cause()}""")
        }
    }
}
