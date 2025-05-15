package com.peter.truassessment.common.network

import coil3.network.HttpException
import com.peter.truassessment.common.network.exceptions.NoInternetException
import com.peter.truassessment.home.domain.models.ArticleModel
import retrofit2.Response
import java.io.IOException

interface SafeCallable {
    suspend fun <T, R> safeCall(
        request: suspend () -> Response<T>,
        mapResponse: (T) -> R
    ): Result<R> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val mappedResponse = mapResponse(body)
                    Result.success(mappedResponse)
                } else {
                    Result.failure(Throwable("Empty Body"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Throwable("API Error ${response.code()}: $errorBody"))
            }
        } catch (e: IOException) {
            Result.failure(NoInternetException())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}