import io.vertx.reactivex.core.http.HttpServer
import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.handler.StaticHandler
import routers.Lab1Router

val server: HttpServer = vertx.createHttpServer()
val router: Router = Router.router(vertx)

val apiRouter: Router = Router.router(vertx).apply {
    mountSubRouter("/lab1", Lab1Router)
}

fun startUp() {
    server.requestHandler(router).listen(8080)
}

fun setUpHandler() {
    router.route("/*").handler(StaticHandler.create("static"))
    router.mountSubRouter("/api", apiRouter)
}