package com.murali.album.model.localdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murali.album.model.entities.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM table_album")
    suspend fun getAlbums(): MutableList<Album>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(list: MutableList<Album>?)

    @Query("DELETE FROM table_album")
    suspend fun deleteAlbums()
}