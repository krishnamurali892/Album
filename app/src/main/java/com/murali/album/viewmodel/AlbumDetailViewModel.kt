package com.murali.album.viewmodel

import com.murali.album.model.AlbumDetail
import com.murali.album.model.AlbumRepository
import com.murali.album.utils.ServerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val albumRepository: AlbumRepository):
    BaseViewModel(){

    fun getAlbumDetails() {
        liveData.value = ServerResponse(ServerResponse.Status.LOADING, null, null)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val requestCall = albumRepository.getAlbumDetails()
            withContext(Dispatchers.Main) {
                requestCall.enqueue(object : Callback<List<AlbumDetail>> {
                    override fun onResponse(call: Call<List<AlbumDetail>>, response: Response<List<AlbumDetail>>) {
                        if (response.isSuccessful) {
                            liveData.value =
                                ServerResponse(ServerResponse.Status.SUCCESS, null, response.body())
                        }
                    }
                    override fun onFailure(call: Call<List<AlbumDetail>>, throwable: Throwable) {
                        liveData.value =
                            ServerResponse(ServerResponse.Status.ERROR, "Error occured ${throwable.localizedMessage}", null)
                    }
                })
            }
        }
    }

}