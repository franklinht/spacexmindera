package com.frank.spacexmindera.data.api

data class Response<out T>(val statusReponse: StatusResponse, val data: T?) {
    companion object {
        fun <T> success(data: T): Response<T> = Response(statusReponse = StatusResponse.SUCCESS, data = data)
        fun <T> error(data: T?): Response<T> = Response(statusReponse = StatusResponse.ERROR, data = data)
        fun <T> loading(data: T?): Response<T> = Response(statusReponse = StatusResponse.LOADING, data = data)
    }
}