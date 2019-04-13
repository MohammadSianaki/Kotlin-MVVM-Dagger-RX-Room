package ir.siatech.kotlinmvvm.data.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.observers.DisposableSingleObserver
import ir.siatech.kotlinmvvm.data.model.NewsResponse
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.SocketTimeoutException

const val TAG = "CustomDisposable"

class CustomDisposable<T>(
    private val success: (responseBody: T) -> Unit,
    private val failure: (error: ApiError) -> Unit = {}
) : DisposableSingleObserver<T>() {
    override fun onSuccess(t: T) = when (t) {
        t == null, (t is NewsResponse && t.articles.isEmpty()) -> failure(ApiError(ApiError.ApiStatus.EMPTY_RESPONSE))
        else -> success(t)
    }

    override fun onError(e: Throwable) {
        Log.d(TAG, "onError : $e")
        when (e) {
            is HttpException -> {
                val errorMessage = getErrorMessage(e.response().errorBody())
                failure(ApiError(ApiError.ApiStatus.BAD_RESPONSE, e.code(), errorMessage))
            }
            is SocketTimeoutException -> failure(
                ApiError(
                    ApiError.ApiStatus.BAD_RESPONSE,
                    message = e.localizedMessage
                )
            )

            is NullPointerException -> failure(ApiError(ApiError.ApiStatus.EMPTY_RESPONSE))
            else -> failure(ApiError(ApiError.ApiStatus.NOT_DEFINED))
        }

    }

    private fun getErrorMessage(errorBody: ResponseBody?): String = try {
        val result = errorBody?.string()
        Log.d(TAG, "getErrorMessage() called with: errorBody = [$result]")
        val json = Gson().fromJson(result, JsonObject::class.java)
        json.toString()
    } catch (t: Throwable) {
        ""
    }
}