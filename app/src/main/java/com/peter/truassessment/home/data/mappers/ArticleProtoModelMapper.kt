package com.peter.truassessment.home.data.mappers

import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.proto.ArticleProtoModel


fun ArticleModel.mapToProtoArticleModel(): ArticleProtoModel {
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