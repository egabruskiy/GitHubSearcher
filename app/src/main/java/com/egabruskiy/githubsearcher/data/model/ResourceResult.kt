package com.egabruskiy.githubsearcher.data.model

import java.io.IOException

sealed class ResourceResult<out T> {

    data class Success<T>(val data: T) : ResourceResult<T>()

    data class Error(val exception: Throwable) : ResourceResult<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Empty : ResourceResult<Nothing>()

    object Loading : ResourceResult<Nothing>()


    companion object{
        fun loading() = Loading

        fun <T> successOrNull(any:T): ResourceResult<T> {
            return if (any==null) Empty else Success(any)
        }
        fun <T> successOrEmpty(list:List<T>): ResourceResult<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }

        fun error(exception: Throwable) = Error(exception)

    }

}