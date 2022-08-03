package com.example.weatherru.repository

import com.example.weatherru.api.ApiResponse
import com.example.weatherru.api.model.ErrorHandler
import retrofit2.Response

abstract class BaseApiRepository(private val errorHandler: ErrorHandler) {

    protected suspend fun <T : Any> apiCall(block: suspend () -> Response<T>) = try {
        val response = block()
        if (response.isSuccessful) {
            ApiResponse.success(response)
        } else {
            errorHandler.handle(response)
        }
    } catch (e: Exception) {
        errorHandler.handle(e)
    }
}