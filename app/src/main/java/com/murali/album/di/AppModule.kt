package com.murali.album.di

import android.content.Context
import com.murali.album.model.AlbumRepository
import com.murali.album.model.localdata.AlbumDao
import com.murali.album.model.remotedata.AlbumApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAlbumRepository(@ApplicationContext context: Context, albumApi: AlbumApi,
                               albumDao: AlbumDao) = AlbumRepository(context, albumApi, albumDao)
}