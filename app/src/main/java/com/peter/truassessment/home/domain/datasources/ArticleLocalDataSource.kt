package com.peter.truassessment.home.domain.datasources

import com.peter.truassessment.home.domain.models.ArticleModel

interface ArticleLocalDataSource {
    suspend fun getArticles(): Result<List<ArticleModel>>
    suspend fun saveArticles(articles: List<ArticleModel>)
}