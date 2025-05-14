package com.peter.truassessment.home.data.dtos

import com.google.gson.annotations.SerializedName

data class SubredditDTO(
    val data: SubredditData
)

data class SubredditData(
    val children: List<ArticleDTO>
)

data class ArticleDTO(
    val data: ArticleDataDTO
)

data class ArticleDataDTO(
    @SerializedName("selftext") val body: String,
    val title: String
)