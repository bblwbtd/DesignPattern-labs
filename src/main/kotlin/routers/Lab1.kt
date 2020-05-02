package routers

import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.RoutingContext
import io.vertx.reactivex.ext.web.handler.BodyHandler
import lab1.AbstractCalculationProvider
import vertx

val Lab1Router: Router = Router.router(vertx).apply {
    route().handler(BodyHandler.create())
    post("/calculate").handler(::performCalculation)
}

fun performCalculation(context: RoutingContext) {
    AbstractCalculationProvider.getInstance().apply {

    }
}