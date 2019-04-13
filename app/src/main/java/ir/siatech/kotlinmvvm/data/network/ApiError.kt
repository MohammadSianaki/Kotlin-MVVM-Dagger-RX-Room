package ir.siatech.kotlinmvvm.data.network

data class ApiError(
    val status: ApiStatus,
    val code: Int = -1,
    var message: String = ""
) {

    fun getErrorMessage(): String = when (status) {
        ApiStatus.NO_CONNECTION -> "Error in connecting to repository"
        ApiStatus.BAD_RESPONSE -> "Error in getting response "
        ApiStatus.TIMEOUT -> " Time out  error"
        ApiStatus.NOT_DEFINED -> "An unexpected error"
        ApiStatus.EMPTY_RESPONSE -> "No data available in repository"
    }

    enum class ApiStatus {
        NO_CONNECTION,
        BAD_RESPONSE,
        TIMEOUT,
        EMPTY_RESPONSE,
        NOT_DEFINED
    }
}