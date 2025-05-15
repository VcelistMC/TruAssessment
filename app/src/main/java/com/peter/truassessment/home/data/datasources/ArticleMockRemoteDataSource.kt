package com.peter.truassessment.home.data.datasources

import com.peter.truassessment.home.domain.datasources.ArticleRemoteDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class ArticleMockRemoteDataSource @Inject constructor():
    ArticleRemoteDataSource {
    override suspend fun getArticles(): Result<List<ArticleModel>> {
        delay(2500)
        val articlesList =  listOf(
            ArticleModel.textMock,
            ArticleModel.textMock,
            ArticleModel.imageMock,
            ArticleModel.textMock,
            ArticleModel.imageMock,
            ArticleModel.imageMock,
        )
        return Result.success(articlesList)
    }
}