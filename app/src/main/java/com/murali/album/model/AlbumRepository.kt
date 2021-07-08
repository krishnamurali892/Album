package com.murali.album.model

import com.murali.album.utils.ServerResponse
import kotlinx.coroutines.delay

class AlbumRepository(private val albumApi: AlbumApi){

    suspend fun getAlbums(): ServerResponse{
        try {
            //Introduced unblocking delay for testing
            delay(20000)
            val response = albumApi.getAlbums()
            if(response.isSuccessful){
                val body = response.body()
                if(body!=null){
                    return ServerResponse(ServerResponse.Status.SUCCESS, null, response.body())
                }
            }
            return ServerResponse(ServerResponse.Status.ERROR, "Error occurred ${response.code()} ${response.message()}", null)
        }catch (e: Exception){
           return ServerResponse(ServerResponse.Status.ERROR, "Error occurred ${e.message ?: e.toString()}", null)
        }
    }

    suspend fun getAlbumDetails(): ServerResponse{
        try {
            val response = albumApi.getAlbumDetails()
            if(response.isSuccessful){
                val body = response.body()
                if(body != null){
                    return ServerResponse(ServerResponse.Status.SUCCESS, null, response.body())
                }
            }
            return ServerResponse(ServerResponse.Status.ERROR, "Error occurred ${response.code()} ${response.message()}", null)
        }catch (e: Exception){
            return ServerResponse(ServerResponse.Status.ERROR, "Error occurred ${e.message ?: e.toString()}", null)
        }
    }

}