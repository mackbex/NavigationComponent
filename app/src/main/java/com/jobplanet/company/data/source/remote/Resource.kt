package com.jobplanet.company.data.source.remote

sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val msg: String?) : Resource<Nothing>()
}