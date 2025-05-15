package com.peter.truassessment.home.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.home.ui.composables.ArticleUiItem
import com.peter.truassessment.home.ui.intent.HomeIntent
import com.peter.truassessment.home.ui.state.HomeScreenState
import com.peter.truassessment.home.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

    HomeScreenContent(
        modifier = modifier,
        screenState = screenState.value,
        onArticleItemClicked = { viewModel.handleIntent(HomeIntent.ViewArticle(it)) },
        onRefreshClicked = { viewModel.handleIntent(HomeIntent.LoadArticles) }
    )

}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    screenState: HomeScreenState,
    onArticleItemClicked: (ArticleModel) -> Unit,
    onRefreshClicked: () -> Unit
){
    Scaffold(
        modifier = modifier.padding(horizontal = 8.dp),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = "Kotlin News",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )

                IconButton(onClick = onRefreshClicked) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null
                    )
                }
            }
        }
    ) { padding ->
        if(screenState.isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }else if(screenState.exception != null){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = screenState.exception.message?: "Unknown")
            }
        }else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(screenState.articlesList) {
                    ArticleUiItem(
                        article = it,
                        onClick = { onArticleItemClicked(it) }
                    )
                }
            }
        }
    }
}