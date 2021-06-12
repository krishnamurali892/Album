package com.murali.album.di

import com.murali.album.model.AlbumApi
import com.murali.album.model.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://my-json-server.typicode.com/krishnamurali892/mockjson/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(provideBaseUrl())
        }.build()

    @Singleton
    @Provides
    fun provideAlbumListService(retrofit: Retrofit): AlbumApi = retrofit.create(AlbumApi::class.java)

    @Singleton
    @Provides
    fun provideAlbumRepository(albumApi: AlbumApi) = AlbumRepository(albumApi)
}