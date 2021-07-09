package com.murali.album.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.murali.album.model.entities.AlbumDetail
import com.murali.album.model.AlbumRepository
import com.murali.album.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val albumRepository: AlbumRepository):
    BaseViewModel(){

    private var _livedata = MutableLiveData<Resource<List<AlbumDetail>?>>()

    val livedata: LiveData<Resource<List<AlbumDetail>?>>
        get() = _livedata

    fun getAlbumDetails() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _livedata.postValue(Resource.Loading)
            _livedata.postValue(albumRepository.getAlbumDetails())
        }
    }

    override fun onError(message: String) {
        _livedata.value = Resource.Failure("Error: $message")
    }
}