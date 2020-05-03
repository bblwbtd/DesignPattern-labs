package routers

import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.handler.BodyHandler
import vertx

val lab2Router = Router.router(vertx).apply {
    route().handler(BodyHandler.create())
    post("")
}

