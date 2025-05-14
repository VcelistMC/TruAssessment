package com.peter.truassessment.home.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.peter.truassessment.home.data.models.ArticleModel
import com.peter.truassessment.home.ui.composables.ArticleUiItem
import com.peter.truassessment.home.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<HomeViewModel>()

    HomeScreenContent(
        modifier = modifier,
        articleList = viewModel.exampleList,
        onArticleItemClicked = viewModel::onArticleClicked
    )

}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    articleList: List<ArticleModel>,
    onArticleItemClicked: (ArticleModel) -> Unit
){
    Scaffold(
        modifier = modifier.padding(horizontal = 8.dp),
        topBar = {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Kotlin News",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(articleList){
                ArticleUiItem(
                    article = it,
                    onClick = { onArticleItemClicked(it) }
                )
            }
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreenContent(
        articleList = listOf(ArticleModel.textMock, ArticleModel.imageMock, ArticleModel.imageAndTextMock),
        onArticleItemClicked = {}
    )
}