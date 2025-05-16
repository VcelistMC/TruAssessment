package com.peter.truassessment.home.data.mappers

import com.peter.truassessment.home.data.dtos.SubredditDTO
import com.peter.truassessment.home.domain.models.ArticleModel

import com.peter.truassessment.proto.ArticleProtoModel

fun SubredditDTO.getArticlesModels(): List<ArticleModel> {
    return this.data.children.map {
        ArticleModel(
            title = it.data.title,
            body = it.data.body,
            imageUrl = it.data.imageUrl
        )
    }
}

fun ArticleProtoModel.mapToDomainArticleModel(): ArticleModel{
    return ArticleModel(
        title = title,
        body = body,
        imageUrl = imageUrl
    )
}

fun ArticleModel.mapToProtoArticleModel(): ArticleProtoModel{
    return ArticleProtoModel.newBuilder()
        .setTitle(title)
        .setBody(body)
        .also { articleProtoModel ->
            imageUrl?.let {
                articleProtoModel.setImageUrl(imageUrl)
            }
        }
        .build()
}