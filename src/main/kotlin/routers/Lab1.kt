package routers

import extension.respondJson
import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.RoutingContext
import io.vertx.reactivex.ext.web.handler.BodyHandler
import lab1.AbstractCalculationProvider
import lab1.bean.CalculationRequest
import vertx

val Lab1Router: Router = Router.router(vertx).apply {
    route().handler(BodyHandler.create())
    post("/calculate").handler(::calculate)

}

fun calculate(context: RoutingContext) {
    context.apply {
        val request = bodyAsJson.mapTo(CalculationRequest::class.java)
        AbstractCalculationProvider.getInstance().performCalculation(request).let {
            respondJson(it)
        }
    }
}