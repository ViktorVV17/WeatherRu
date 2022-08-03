package com.example.weatherru.features.forecastdetails.domain

import com.example.weatherru.api.ApiResponse
import com.example.weatherru.api.MainApiService
import com.example.weatherru.api.model.ErrorHandler
import com.example.weatherru.features.forecastdetails.data.ForecastResponse
import com.example.weatherru.repository.BaseApiRepository

class ForecastRepository(
    private val apiService: MainApiService,
    errorHandler: ErrorHandler
) : BaseApiRepository(errorHandler) {

    suspend fun getForecast(lat: String, lon: String): ApiResponse<ForecastResponse> {
        return apiCall { apiService.getForecast(lat, lon) }
    }
}