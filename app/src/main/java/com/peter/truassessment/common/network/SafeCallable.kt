package com.peter.truassessment.common.network

import com.peter.truassessment.common.network.exceptions.EmptyBodyException
import com.peter.truassessment.common.network.exceptions.NoInternetException
import retrofit2.Response
import java.io.IOException

interface SafeCallable {
    suspend fun <T, R> safeCall(
        request: suspend () -> Response<T>,
        mapResponse: (T) -> R
    ): Result<R> {
        return runCatching { request() }
            .mapCatching { response ->
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()?.string()
                    throw Throwable("API Error ${response.code()}: $errorBody")
                }

                val body = response.body() ?: throw EmptyBodyException()
                mapResponse(body)
            }
            .recoverCatching { e ->
                when (e) {
                    is IOException -> throw NoInternetException()
                    else -> throw e
                }
            }
    }
}