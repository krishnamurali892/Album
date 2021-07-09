package com.murali.album.model.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.murali.album.model.entities.Album

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class AlbumDatabase: RoomDatabase() {
    abstract fun getAlbumDao(): AlbumDao
}