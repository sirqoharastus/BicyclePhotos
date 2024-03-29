package com.abdulqohar.bicyclephotos.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

object ApiCallHandler {

    const val MESSAGE_KEY = "message"
    const val ERROR_KEY = "error"

    suspend inline fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        crossinline apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(dispatcher) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (exception: Exception) {
                when (exception) {
                    is IOException -> Resource.Error("IO Error! Could not Connect to the Internet")
                    is HttpException -> {
                        val code = exception.code()
                        val errorResponse = exception.response()?.errorBody()?.let {
                            getErrorMessage(it)
                        }
                        Resource.Error("Error $code -> $errorResponse")
                    }

                    is UnknownHostException -> Resource.Error("An error occurred, please check your network connection")
                    else -> {
                        Resource.Error("An error has occurred", null)
                    }
                }
            }
        }
    }

    fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }


}