package com.peter.truassessment.home.ui.intent

import com.peter.truassessment.common.ui.Intent
import com.peter.truassessment.home.domain.models.ArticleModel

sealed class HomeIntent: Intent {
    data object LoadArticles: HomeIntent()
    data class ViewArticle(val articleToView: ArticleModel): HomeIntent()
}