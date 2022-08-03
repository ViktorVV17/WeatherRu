package com.example.weatherru.features.main.domain

import com.example.weatherru.api.ApiResponse
import com.example.weatherru.api.MainApiService
import com.example.weatherru.api.model.ErrorHandler
import com.example.weatherru.features.main.data.WeatherResponse
import com.example.weatherru.repository.BaseApiRepository

class MainRepository(
    private val apiService: MainApiService,
    errorHandler: ErrorHandler
) : BaseApiRepository(errorHandler) {

    suspend fun getCityWeather(lat: String, lon: String): ApiResponse<WeatherResponse> {
        return apiCall { apiService.getCityWeather(lat, lon) }
    }
}