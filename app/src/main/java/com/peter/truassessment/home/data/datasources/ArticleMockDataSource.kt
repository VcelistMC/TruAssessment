package com.peter.truassessment.home.data.datasources

import com.peter.truassessment.home.domain.datasources.ArticleDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class ArticleMockDataSource @Inject constructor(): ArticleDataSource {
    override suspend fun getArticles(): List<ArticleModel> {
        delay(2500)
        return listOf(
            ArticleModel.textMock,
            ArticleModel.textMock,
            ArticleModel.imageMock,
            ArticleModel.textMock,
            ArticleModel.imageMock,
            ArticleModel.imageMock,
        )
    }
}