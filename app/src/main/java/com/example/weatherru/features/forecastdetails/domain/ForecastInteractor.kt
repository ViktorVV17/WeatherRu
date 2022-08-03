package com.example.weatherru.features.forecastdetails.domain

import com.example.weatherru.api.ApiSuccessResponse
import com.example.weatherru.features.forecastdetails.data.item.ForecastListItem
import com.example.weatherru.features.forecastdetails.mapper.ForecastMapper

class ForecastInteractor(
    private var repository: ForecastRepository,
    private val mapper: ForecastMapper
) {

    suspend fun getForecast(lat: String, lon: String): List<ForecastListItem> {
        return when (val response = repository.getForecast(lat, lon)) {
            is ApiSuccessResponse -> mapper.applyForecast(response.body)
            //is ApiRegularError -> throw LoadFailedException(response.msg) todo handle exceptions
            //is ApiCommonError -> throw LoadFailedException(response.errorMessage)
            else -> emptyList() //throw LoadFailedException("Not response")
        }
    }
}