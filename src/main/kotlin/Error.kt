enum class Error(val code: Int, val message: String) {
    EmptyBody(-1, "Empty body"),
    InvalidRequest(-2, "Invalid request");
}