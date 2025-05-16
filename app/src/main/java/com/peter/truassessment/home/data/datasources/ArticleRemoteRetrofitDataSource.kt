package com.peter.truassessment.home.data.datasources

import com.peter.truassessment.common.network.SafeCallable
import com.peter.truassessment.home.data.mappers.getArticlesModels
import com.peter.truassessment.home.data.remote.ArticlesApi
import com.peter.truassessment.home.domain.datasources.ArticleRemoteDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import javax.inject.Inject

class ArticleRemoteRetrofitDataSource @Inject constructor(
    private val articlesApi: ArticlesApi
) : ArticleRemoteDataSource, SafeCallable {
    override suspend fun getArticles(): Result<List<ArticleModel>> {
        return safeCall(
            request = { articlesApi.getArticles() },
            mapResponse = { dto ->
                dto.getArticlesModels()
            }
        )
    }
}