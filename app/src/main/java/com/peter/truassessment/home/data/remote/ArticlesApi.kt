package com.peter.truassessment.home.data.remote

import retrofit2.http.GET

interface ArticlesApi {
    @GET
    suspend fun getArticles(): List<>
}