package extension

import io.vertx.core.json.JsonObject
import io.vertx.reactivex.ext.web.RoutingContext

fun RoutingContext.respondJson(data: Any?, code: Int = 0) {
    response().putHeader("content-type", "application/json").rxEnd(JsonObject(mapOf(
            "code" to code,
            "data" to JsonObject.mapFrom(data)
    )).toString()).subscribe()
}