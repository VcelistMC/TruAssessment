package com.peter.truassessment.home.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.peter.truassessment.home.data.models.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    val exampleList = listOf(ArticleModel.textMock, ArticleModel.imageMock, ArticleModel.imageAndTextMock)

    fun onArticleClicked(clickedArticle: ArticleModel){}
}