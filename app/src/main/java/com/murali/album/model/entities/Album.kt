package com.murali.album.model.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel

@Entity(tableName = "table_album")
@Parcelize
data class Album(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
) : Parcelable{
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}