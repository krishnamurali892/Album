package com.murali.album.model

import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {
    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("photos")
    suspend fun getAlbumDetails(): Response<List<AlbumDetail>>
}