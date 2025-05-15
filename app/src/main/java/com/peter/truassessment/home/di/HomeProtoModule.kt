package com.peter.truassessment.home.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.peter.truassessment.home.data.proto.PROTO_DATA_STORE_FILENAME
import com.peter.truassessment.home.data.proto.articleListDataStore
import com.peter.truassessment.home.data.proto.serializers.ArticleListSerializer
import com.peter.truassessment.proto.ArticleList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeProtoModule {
    @Singleton
    @Provides
    fun provideArticleListProtoInstance(
        @ApplicationContext context: Context
    ): DataStore<ArticleList>{
        return DataStoreFactory.create(
            serializer = ArticleListSerializer
        ){
            context.dataStoreFile(PROTO_DATA_STORE_FILENAME)
        }
    }
}