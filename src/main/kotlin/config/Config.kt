package config

fun getHttpServiceConfig(): HttpServiceConfig {
    val env = System.getenv()
    return HttpServiceConfig(env["HOST"] ?: "0.0.0.0", env["PORT"]?.toInt() ?: 8080)
}