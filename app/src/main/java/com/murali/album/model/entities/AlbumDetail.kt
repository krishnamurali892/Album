package com.murali.album.model.entities

import com.google.gson.annotations.SerializedName

data class AlbumDetail(
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)