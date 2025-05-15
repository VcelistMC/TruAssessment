package com.peter.truassessment.home.data.datasources

import coil3.network.HttpException
import com.peter.truassessment.common.network.SafeCallable
import com.peter.truassessment.home.data.mappers.getArticlesModels
import com.peter.truassessment.home.data.remote.ArticlesApi
import com.peter.truassessment.home.domain.datasources.ArticleDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import java.io.IOException
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val articlesApi: ArticlesApi
) : ArticleDataSource, SafeCallable {
    override suspend fun getArticles(): Result<List<ArticleModel>> {
        return safeCall(
            request = { articlesApi.getArticles() },
            mapResponse = { dto ->
                dto.getArticlesModels()
            }
        )
    }
}