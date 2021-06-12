package com.murali.album.model

class AlbumRepository(private val albumApi: AlbumApi){
    fun getAlbums() = albumApi.getAlbums()
    fun getAlbumDetails() = albumApi.getAlbumDetails()
}