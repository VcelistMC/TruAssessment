package com.peter.truassessment.home.data.repo

import com.peter.truassessment.home.data.datasources.ArticleMockRemoteDataSource
import com.peter.truassessment.home.data.datasources.ArticleRemoteRetrofitDataSource
import com.peter.truassessment.home.data.datasources.ArticleLocalProtoDataSource
import com.peter.truassessment.home.domain.datasources.ArticleLocalDataSource
import com.peter.truassessment.home.domain.datasources.ArticleRemoteDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.home.domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepoImpl @Inject constructor(
    private val remoteDataSource: ArticleRemoteDataSource,
    private val localDataSource: ArticleLocalDataSource
): ArticleRepo {
    override fun getArticles(): Flow<Result<List<ArticleModel>>> = flow {
        remoteDataSource.getArticles()
            .onSuccess { articles ->
                emit(Result.success(articles))
                localDataSource.saveArticles(articles)
            }
            .onFailure { remoteError ->
                localDataSource.getArticles()
                    .onSuccess { cached ->
                        if (cached.isEmpty()) {
                            emit(Result.failure(remoteError))
                        } else {
                            emit(Result.success(cached))
                        }
                    }
                    .onFailure { localError ->
                        emit(Result.failure(localError))
                    }
            }
    }

}