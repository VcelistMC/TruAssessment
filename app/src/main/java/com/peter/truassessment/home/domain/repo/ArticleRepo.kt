package com.peter.truassessment.home.domain.repo

import com.peter.truassessment.home.domain.models.ArticleModel
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {
    fun getArticles(): Flow<Result<List<ArticleModel>>>
}