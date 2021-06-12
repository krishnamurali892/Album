package com.murali.album.utils

data class ServerResponse(val status: Status,
                          val message: String?, val data: List<Any>?){
    enum class Status() {
        SUCCESS,
        ERROR,
        LOADING
    }
}
