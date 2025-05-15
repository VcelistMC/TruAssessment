package com.peter.truassessment.home.ui.navtypes

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.peter.truassessment.home.domain.models.ArticleModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ArticleNavType : NavType<ArticleModel>(
    isNullableAllowed = false
){
    override fun serializeAsValue(value: ArticleModel): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun get(bundle: SavedState, key: String): ArticleModel? {
        return bundle.getString(key)?.let {
            Json.decodeFromString(
                it
            )
        }
    }

    override fun parseValue(value: String): ArticleModel {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: SavedState, key: String, value: ArticleModel) {
        bundle.putString(key, Json.encodeToString(value))
    }
}