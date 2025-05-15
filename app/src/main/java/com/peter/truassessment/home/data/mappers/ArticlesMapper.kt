package com.peter.truassessment.home.data.mappers

import com.peter.truassessment.home.data.dtos.SubredditDTO
import com.peter.truassessment.home.domain.models.ArticleModel

fun SubredditDTO.getArticlesModels(): List<ArticleModel> {
    return this.data.children.map {
        ArticleModel(
            title = it.data.title,
            body = it.data.body,
            imageUrl = it.data.imageUrl
        )
    }
}