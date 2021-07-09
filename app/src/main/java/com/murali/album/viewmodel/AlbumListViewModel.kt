package com.murali.album.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.murali.album.model.entities.Album
import com.murali.album.model.AlbumRepository
import com.murali.album.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    BaseViewModel() {

    private var _livedata = MutableLiveData<Resource<List<Album>?>>()

    val livedata: LiveData<Resource<List<Album>?>>
        get() = _livedata

    fun getAlbums() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _livedata.postValue(Resource.Loading)
            _livedata.postValue(albumRepository.getAlbums())
        }
    }

    override fun onError(message: String) {
        _livedata.value = Resource.Failure("Error: $message")
    }
}