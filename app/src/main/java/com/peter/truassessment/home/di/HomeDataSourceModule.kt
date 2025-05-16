package com.peter.truassessment.home.di

import com.peter.truassessment.home.data.datasources.ArticleLocalProtoDataSource
import com.peter.truassessment.home.data.datasources.ArticleRemoteRetrofitDataSource
import com.peter.truassessment.home.domain.datasources.ArticleLocalDataSource
import com.peter.truassessment.home.domain.datasources.ArticleRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(
        remoteRetrofitDataSource: ArticleRemoteRetrofitDataSource
    ): ArticleRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(
        localProtoDataSource: ArticleLocalProtoDataSource
    ): ArticleLocalDataSource
}