package com.peter.truassessment.home.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.peter.truassessment.common.ui.BaseViewModel
import com.peter.truassessment.home.domain.repo.ArticleRepo
import com.peter.truassessment.home.ui.intent.HomeIntent
import com.peter.truassessment.home.ui.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepo: ArticleRepo
): BaseViewModel<HomeScreenState, HomeIntent>() {
    init {
        handleIntent(HomeIntent.LoadArticles)
    }

    override fun initialState(): HomeScreenState {
        return HomeScreenState(
            isLoading = false,
            articlesList = emptyList(),
            exception = null
        )
    }

    override fun handleIntent(intent: HomeIntent) {
        when(intent){
            is HomeIntent.LoadArticles -> loadArticles()
            is HomeIntent.ViewArticle -> {}
        }
    }

    private fun loadArticles(){
        viewModelScope.launch {
            // I personally wouldn't use flows for one-shot api calls, and I would use simple coroutines
            // but since flows is the preferred stack, I'm using flows
            articleRepo.getArticles()
                .onStart { updateState { it.copy(isLoading = true) } }
                .collect { result ->
                    if(result.isSuccess){
                        updateState {
                            it.copy(
                                isLoading = false,
                                articlesList = result.getOrDefault(emptyList()),
                                exception = null
                            )
                        }
                    }else{
                        updateState {
                            it.copy(
                                isLoading = false,
                                exception = result.exceptionOrNull()?: Throwable("Failed")
                            )
                        }
                    }
                }
        }
    }
}