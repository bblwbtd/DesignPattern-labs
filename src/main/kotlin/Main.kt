import config.getHttpServiceConfig
import io.vertx.reactivex.core.Vertx

val vertx = Vertx.vertx()

fun main() {
    startUpHttpService(getHttpServiceConfig())
}