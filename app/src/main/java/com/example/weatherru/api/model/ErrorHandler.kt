package com.example.weatherru.api.model

import com.example.weatherru.api.ApiResponse
import com.google.gson.Gson
import retrofit2.Response

object ErrorHandler {

    fun <T> handle(throwable: Throwable): ApiResponse<T> {
        return ApiResponse.error(throwable)
    }

    fun <T> handle(response: Response<T>): ApiResponse<T> {
        return ApiResponse.error(response, Gson())
    }
}