package com.murali.album.model

import retrofit2.Call
import retrofit2.http.GET

interface AlbumApi {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("photos")
    fun getAlbumDetails(): Call<List<AlbumDetail>>
}