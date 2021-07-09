package com.murali.album.utils

sealed class Resource<out T>{
    object Loading : Resource<Nothing>()
    data class Success<out T>(val body: T) : Resource<T>()
    data class Failure(val exceptionMessage: String) : Resource<Nothing>()
}
