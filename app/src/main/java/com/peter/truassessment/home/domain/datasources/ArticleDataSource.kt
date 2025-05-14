package com.peter.truassessment.home.domain.datasources

import com.peter.truassessment.home.domain.models.ArticleModel

interface ArticleDataSource {
    suspend fun getArticles(): List<ArticleModel>
}