package com.peter.truassessment.home.di

import com.peter.truassessment.home.data.remote.ArticlesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeNetworkModule {

    @Provides
    @Singleton
    fun provideArticlesApi(): ArticlesApi {
        return Retrofit.Builder()
            .baseUrl("https://www.reddit.com/r/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticlesApi::class.java)
    }
}