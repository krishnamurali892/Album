package com.murali.album.viewmodel

import com.murali.album.model.Album
import com.murali.album.model.AlbumRepository
import com.murali.album.utils.ServerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    BaseViewModel() {

    fun getAlbums() {
        liveData.value = ServerResponse(ServerResponse.Status.LOADING, null, null)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val requestCall = albumRepository.getAlbums()
            withContext(Dispatchers.Main) {
                requestCall.enqueue(object : Callback<List<Album>> {
                    override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                        if (response.isSuccessful) {
                            liveData.value =
                                ServerResponse(ServerResponse.Status.SUCCESS, null, response.body())
                        }
                    }
                    override fun onFailure(call: Call<List<Album>>, throwable: Throwable) {
                        liveData.value =
                            ServerResponse(ServerResponse.Status.ERROR, "Error occured ${throwable.localizedMessage}", null)
                    }
                })
            }
        }
    }
}