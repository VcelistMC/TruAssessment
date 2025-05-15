package com.peter.truassessment.home.data.repo

import com.peter.truassessment.home.data.datasources.ArticleMockRemoteDataSource
import com.peter.truassessment.home.data.datasources.ArticleRemoteDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.home.domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepoImpl @Inject constructor(
    private val mockDataSource: ArticleMockRemoteDataSource,
    private val remoteDataSource: ArticleRemoteDataSource
): ArticleRepo {
    override fun getArticles(): Flow<Result<List<ArticleModel>>> {
        return flow {
            emit(remoteDataSource.getArticles())
        }
    }
}