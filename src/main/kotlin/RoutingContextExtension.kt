import io.vertx.core.json.JsonObject
import io.vertx.reactivex.ext.web.RoutingContext

fun RoutingContext.respondJson(data: Any? = null, code: Int = 0) {
    response().putHeader("content-type", "application/json").rxEnd(JsonObject(mapOf(
            "code" to code,
            "data" to data
    )).toString()).subscribe()
}

fun RoutingContext.respondSuccess() {
    respondJson()
}

fun RoutingContext.respondError(errors: Errors) {
    respondJson(errors.message, errors.code)
}

fun RoutingContext.getBodyFromContext(): JsonObject {
    return get("body")
}

fun <T> RoutingContext.mapBodyTo(type: Class<T>, handler: (T) -> Unit) {
    try {
        val body = bodyAsJson
        if (body == null) {
            respondError(Errors.EmptyBody)
        } else {
            handler(body.mapTo(type))
        }
    } catch (e: Exception) {
        respondError(Errors.InvalidRequest)
    }
}