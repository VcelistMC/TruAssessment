package com.peter.truassessment.home.domain.usecases

import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.home.domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val articleRepo: ArticleRepo
) {
    operator fun invoke(): Flow<Result<List<ArticleModel>>>{
        return articleRepo.getArticles()
    }
}