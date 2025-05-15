package com.peter.truassessment.home.domain.datasources

import com.peter.truassessment.home.domain.models.ArticleModel

interface ArticleRemoteDataSource {
    suspend fun getArticles(): Result<List<ArticleModel>>
}