enum class CustomError(val code: Int, val message: String) {
    EmptyBody(-1, "Empty body"),
    InvalidRequest(-2, "Invalid request"),
    UnknownError(-99, "unknown error");
}