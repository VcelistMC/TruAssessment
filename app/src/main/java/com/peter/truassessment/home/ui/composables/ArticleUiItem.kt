package com.peter.truassessment.home.ui.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.peter.truassessment.home.data.models.ArticleModel

@Composable
fun ArticleUiItem(
    modifier: Modifier = Modifier,
    article: ArticleModel,
    onClick: () -> Unit
){
    Column(
        modifier = modifier
            .clickable { onClick() }
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = article.title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        article.imageUrl?.let {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = article.imageUrl,
                contentDescription = null,
            )
        }

        if(article.imageUrl == null && article.body != null){
            Text(
                text = article.body,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}


@Preview()
@Composable
fun ArticleUiItemPreview(){
    ArticleUiItem(
        modifier = Modifier.fillMaxWidth(),
        article = ArticleModel.imageMock,
        onClick = {}
    )
}