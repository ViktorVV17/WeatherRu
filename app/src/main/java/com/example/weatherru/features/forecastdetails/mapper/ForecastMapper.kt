package com.example.weatherru.features.forecastdetails.mapper

import com.example.weatherru.features.forecastdetails.data.ForecastResponse
import com.example.weatherru.features.forecastdetails.data.item.ForecastListItem
import com.example.weatherru.features.main.mapper.MainMapper
import kotlin.math.roundToInt

object ForecastMapper {

    fun applyForecast(response: ForecastResponse) = with(response) {
        list?.map {
            ForecastListItem(
                time = it.time.orEmpty(),//todo add date/time converter
                temperature = it.main?.temperature
                    ?.minus(MainMapper.KELVIN_TO_CELSIUS)?.roundToInt()
                    ?.toString()?.plus(MainMapper.CELSIUS_SIGN).orEmpty(),
                weather = it.weather?.firstOrNull()?.description.orEmpty()
            )
        } ?: emptyList()
    }
}