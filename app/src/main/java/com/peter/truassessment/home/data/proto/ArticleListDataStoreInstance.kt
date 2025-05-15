package com.peter.truassessment.home.data.proto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.peter.truassessment.home.data.proto.serializers.ArticleListSerializer
import com.peter.truassessment.proto.ArticleList

const val PROTO_DATA_STORE_FILENAME = "article_list.json"

val Context.articleListDataStore: DataStore<ArticleList> by dataStore(
    fileName = PROTO_DATA_STORE_FILENAME,
    serializer = ArticleListSerializer
)