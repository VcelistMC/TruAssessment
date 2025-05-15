package com.peter.truassessment.home.ui.state

import com.peter.truassessment.common.ui.State
import com.peter.truassessment.home.domain.models.ArticleModel

data class HomeScreenState(
    val isLoading: Boolean,
    val articlesList: List<ArticleModel>,
    val exception: Throwable?
): State
