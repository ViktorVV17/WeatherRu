package com.example.weatherru.features.main.domain

import com.example.weatherru.api.ApiSuccessResponse
import com.example.weatherru.features.main.data.item.CitiesListItem
import com.example.weatherru.features.main.mapper.MainMapper

class MainInteractor(
    private var repository: MainRepository,
    private val mapper: MainMapper
) {

    suspend fun getCityWeather(lat: String, lon: String): CitiesListItem? {
        return when (val response = repository.getCityWeather(lat, lon)) {
            is ApiSuccessResponse -> mapper.applyCityWeather(response.body)
            //is ApiRegularError -> throw LoadFailedException(response.msg) todo default string resource if needed
            //is ApiCommonError -> throw LoadFailedException(response.errorMessage) todo handle exceptions
            else -> null//throw LoadFailedException("Not response")
        }
    }
}