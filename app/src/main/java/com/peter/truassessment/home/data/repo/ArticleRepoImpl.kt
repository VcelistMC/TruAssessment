package com.peter.truassessment.home.data.repo

import com.peter.truassessment.home.data.datasources.ArticleMockDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.home.domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepoImpl @Inject constructor(
    private val mockDataSource: ArticleMockDataSource
): ArticleRepo {
    override suspend fun getArticles(): Flow<List<ArticleModel>> {
        return flow { emit(mockDataSource.getArticles()) }
    }
}