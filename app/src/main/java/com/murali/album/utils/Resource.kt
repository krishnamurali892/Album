package com.murali.album.utils

/**
 * This is Generic Response holder class
 * And as it is Sealed class when its used in UI with "when"
 * statement to handle response, 'when' statement enforces user to handle
 * all Use cases(i,e child classes of Sealed) and also 'else' case not needed.
 */
sealed class Resource<out T>{
    /*As below classes are inherited from sealed class
    smart casting will happen in UI No need manual casting*/
    object Loading : Resource<Nothing>()
    /*As below are data classes which are meant for holding data.
    enforces to pass at least one param.*/
    data class Success<out T>(val body: T) : Resource<T>()
    data class Failure(val exceptionMessage: String) : Resource<Nothing>()
}
