package com.peter.truassessment

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.peter.truassessment.home.domain.models.ArticleModel
import com.peter.truassessment.home.ui.screens.HomeScreenContent
import com.peter.truassessment.home.ui.state.HomeScreenState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KotlinNewsUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()



    @Test
    fun test_home_screen_showing_loading(){
        val homeScreenState = HomeScreenState(isLoading = true, articlesList = emptyList(), exception = null)
        composeTestRule.setContent {
            HomeScreenContent(
                screenState = homeScreenState,
                onArticleItemClicked = {},
                onRefreshClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("LoadingObject").assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsError_whenExceptionIsPresent() {
        val errorMessage = "Something went wrong"
        val state = HomeScreenState(
            isLoading = false,
            articlesList = emptyList(),
            exception = Exception(errorMessage)
        )

        composeTestRule.setContent {
            HomeScreenContent(
                screenState = state,
                onArticleItemClicked = {},
                onRefreshClicked = {}
            )
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysArticles_whenLoadedSuccessfully() {
        val articles = listOf(
            ArticleModel(title = "Article One", body = "Content 1"),
            ArticleModel(title = "Article Two", body = "Content 2"),
        )

        val state = HomeScreenState(
            isLoading = false,
            articlesList = articles,
            exception = null
        )

        composeTestRule.setContent {
            HomeScreenContent(
                screenState = state,
                onArticleItemClicked = {},
                onRefreshClicked = {}
            )
        }

        composeTestRule.onNodeWithText("Article One").assertIsDisplayed()
        composeTestRule.onNodeWithText("Article Two").assertIsDisplayed()
    }

}