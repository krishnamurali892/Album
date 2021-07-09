package com.murali.album.di

import android.content.Context
import androidx.room.Room
import com.murali.album.model.localdata.AlbumDao
import com.murali.album.model.localdata.AlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext appContext: Context): AlbumDatabase =
        Room.databaseBuilder(appContext, AlbumDatabase::class.java, "albumDB").build()

    @Singleton
    @Provides
    fun provideWeatherDao(albumDatabase: AlbumDatabase): AlbumDao =
        albumDatabase.getAlbumDao()
}