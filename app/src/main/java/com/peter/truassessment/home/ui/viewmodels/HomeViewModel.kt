package com.peter.truassessment.home.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.peter.truassessment.common.ui.BaseViewModel
import com.peter.truassessment.home.domain.repo.ArticleRepo
import com.peter.truassessment.home.domain.usecases.GetArticlesUseCase
import com.peter.truassessment.home.ui.intent.HomeIntent
import com.peter.truassessment.home.ui.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
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
        }
    }

    private fun loadArticles(){
        viewModelScope.launch {
            getArticlesUseCase()
                .onStart { updateState { it.copy(isLoading = true) } }
                .collect { result ->
                    result.onSuccess{ list ->
                        updateState {
                            it.copy(
                                isLoading = false,
                                articlesList = list,
                                exception = null
                            )
                        }
                    }.onFailure { error ->
                        updateState {
                            it.copy(
                                isLoading = false,
                                exception = error
                            )
                        }
                    }
                }
        }
    }
}