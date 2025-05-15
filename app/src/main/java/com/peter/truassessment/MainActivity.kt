package com.peter.truassessment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.home.ui.navtypes.ArticleNavType
import com.peter.truassessment.home.ui.screens.ArticleDetailContent
import com.peter.truassessment.home.ui.screens.ArticleDetailScreen
import com.peter.truassessment.home.ui.screens.ArticleDetailScreenRoute
import com.peter.truassessment.home.ui.screens.HomeScreen
import com.peter.truassessment.home.ui.screens.HomeScreenRoute
import com.peter.truassessment.ui.theme.TRUAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TRUAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = HomeScreenRoute
                    ){
                        composable<HomeScreenRoute> {
                            HomeScreen(
                                onArticleItemClicked = {
                                    navController.navigate(ArticleDetailScreenRoute(clickedArticle = it))
                                }
                            )
                        }
                        composable<ArticleDetailScreenRoute>(
                            typeMap = mapOf(
                                typeOf<ArticleModel>() to ArticleNavType
                            )
                        ) {
                            val clickedArticle = it.toRoute<ArticleDetailScreenRoute>().clickedArticle
                            ArticleDetailScreen(
                                article = clickedArticle,
                                onBackClicked = navController::navigateUp
                            )
                        }
                    }
                }
            }
        }
    }
}