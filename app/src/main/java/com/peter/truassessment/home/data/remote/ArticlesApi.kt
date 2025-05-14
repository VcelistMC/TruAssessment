package com.peter.truassessment.home.data.remote

import com.peter.truassessment.home.data.dtos.SubredditDTO
import retrofit2.Response
import retrofit2.http.GET

interface ArticlesApi {
    @GET("kotlin/.json?raw_json=1")
    suspend fun getArticles(): Response<SubredditDTO>
}