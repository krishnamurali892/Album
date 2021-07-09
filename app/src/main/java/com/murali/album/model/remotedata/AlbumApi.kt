package com.murali.album.model.remotedata

import com.murali.album.model.entities.AlbumDetail
import com.murali.album.model.entities.Album
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {
    @GET("albums")
    suspend fun getAlbums(): Response<MutableList<Album>>

    @GET("photos")
    suspend fun getAlbumDetails(): Response<MutableList<AlbumDetail>>
}