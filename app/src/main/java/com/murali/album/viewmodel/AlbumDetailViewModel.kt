package com.murali.album.viewmodel

import androidx.lifecycle.viewModelScope
import com.murali.album.model.AlbumRepository
import com.murali.album.utils.ServerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val albumRepository: AlbumRepository):
    BaseViewModel(){

    fun getAlbumDetails() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            liveData.postValue(ServerResponse(ServerResponse.Status.LOADING, null, null))
            liveData.postValue(albumRepository.getAlbumDetails())
        }
    }
}