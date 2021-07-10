package com.murali.album.model

import android.content.Context
import com.murali.album.model.entities.Album
import com.murali.album.model.entities.AlbumDetail
import com.murali.album.model.localdata.AlbumDao
import com.murali.album.model.remotedata.AlbumApi
import com.murali.album.utils.Resource
import com.murali.album.utils.isInternetAvailable

class AlbumRepository(private val context: Context, private val albumApi: AlbumApi,
                      private val albumDao: AlbumDao){

    suspend fun getAlbums(): Resource<List<Album>?>{
        if(context.isInternetAvailable()){
            getRemoteData()
        }
        return getLocalData()
    }

    private suspend fun getRemoteData(): Resource<List<Album>?>{
        try {
            val response = albumApi.getAlbums()
            if(response.isSuccessful){
                val body = response.body()
                if(body!=null){
                    albumDao.deleteAlbums()
                    albumDao.insertAlbums(response.body())
                    return Resource.Success(response.body())
                }
            }
            return Resource.Failure("Error occurred ${response.code()} ${response.message()}")
        }catch (e: Exception){
            return Resource.Failure("Error occurred ${e.message ?: e.toString()}")
        }
    }

    private suspend fun getLocalData(): Resource<List<Album>?>{
        try {
            val albums = albumDao.getAlbums()
            albums?.let {
                return Resource.Success(albums)
            }
            return Resource.Failure("Error no offline data")
        } catch (e: Exception) {
            return Resource.Failure(e.message ?: e.toString())
        }
    }

    suspend fun getAlbumDetails(): Resource<List<AlbumDetail>?> {
        try {
            val response = albumApi.getAlbumDetails()
            if(response.isSuccessful){
                val body = response.body()
                if(body != null){
                    return Resource.Success(response.body())
                }
            }
            return Resource.Failure("Error occurred ${response.code()} ${response.message()}")
        }catch (e: Exception){
            return Resource.Failure("Error occurred ${e.message ?: e.toString()}")
        }
    }

}