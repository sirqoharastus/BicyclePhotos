package com.abdulqohar.bicyclephotos.util

import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import org.json.JSONObject


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true}
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map {
                Resource.Success(it)
            }
        } catch (e: Exception) {
            query().map { Resource.Error(e.message?: "", it) }
        }
    } else query().map { Resource.Success(it) }
    emitAll(flow)
}

fun getErrorMessage(responseBody: ResponseBody): String {
    return try {
        val jsonObject = JSONObject(responseBody.string())
        when {
            jsonObject.has(ApiCallHandler.MESSAGE_KEY) -> jsonObject.getString(ApiCallHandler.MESSAGE_KEY)
            jsonObject.has(ApiCallHandler.ERROR_KEY) -> jsonObject.getString(ApiCallHandler.ERROR_KEY)
            else -> "Something wrong happened"
        }
    } catch (e: Exception) {
        "Something wrong happened"
    }
}