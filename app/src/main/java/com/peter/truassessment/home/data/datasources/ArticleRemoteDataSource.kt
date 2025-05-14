package com.peter.truassessment.home.data.datasources

import com.peter.truassessment.home.data.remote.ArticlesApi
import com.peter.truassessment.home.domain.datasources.ArticleDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val articlesApi: ArticlesApi
): ArticleDataSource {
    override suspend fun getArticles(): List<ArticleModel> {
        return articlesApi.getArticles().body()?.let { data ->
            data.data.children.map { child ->
                ArticleModel(
                    title = child.data.title,
                    body = child.data.body
                )
            }
        }?: emptyList()
    }
}