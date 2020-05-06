package routers

import bodyAsJson
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import lab1.AbstractCalculationProvider
import lab1.CalculationProvider
import lab1.bean.CalculationRequest
import respondJson

fun Route.mountLab1Router() {
    route("/lab1") {
        post("/calculate") {
            call.bodyAsJson(CalculationRequest::class.java) {
                val provider: AbstractCalculationProvider = CalculationProvider()
                val result = provider.performCalculation(it)
                call.respondJson(result)
            }
        }
    }
}
