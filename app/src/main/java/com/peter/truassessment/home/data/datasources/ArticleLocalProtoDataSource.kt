package com.peter.truassessment.home.data.datasources

import androidx.datastore.core.DataStore
import com.peter.truassessment.home.data.mappers.mapToDomainArticleModel
import com.peter.truassessment.home.data.mappers.mapToProtoArticleModel
import com.peter.truassessment.home.domain.datasources.ArticleLocalDataSource
import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.proto.ArticleList
import com.peter.truassessment.proto.ArticleProtoModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ArticleLocalProtoDataSource @Inject constructor(
    private val articlesDataStore: DataStore<ArticleList>
): ArticleLocalDataSource {
    override suspend fun getArticles(): Result<List<ArticleModel>> {
        return try {
            val list = articlesDataStore.data.first()
            val mappedArticleList = list.articlesList.map(ArticleProtoModel::mapToDomainArticleModel)

            Result.success(mappedArticleList)
        }catch (exception: Exception){
            Result.failure(exception)
        }
    }

    override suspend fun saveArticles(articles: List<ArticleModel>) {
        articlesDataStore.updateData {
            it.toBuilder().clearArticles()
                .addAllArticles(
                    articles.map(ArticleModel::mapToProtoArticleModel)
                ).build()
        }
    }
}