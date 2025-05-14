package com.peter.truassessment.home.di

import com.peter.truassessment.home.data.datasources.ArticleMockDataSource
import com.peter.truassessment.home.data.repo.ArticleRepoImpl
import com.peter.truassessment.home.domain.repo.ArticleRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    @Singleton
    abstract fun bindArticlesRepo(
        articleRepoImpl: ArticleRepoImpl
    ): ArticleRepo
}