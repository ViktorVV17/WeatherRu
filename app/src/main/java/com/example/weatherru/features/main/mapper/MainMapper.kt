package com.example.weatherru.features.main.mapper

import com.example.weatherru.features.main.data.WeatherResponse
import com.example.weatherru.features.main.data.item.CitiesListItem
import kotlin.math.roundToInt

object MainMapper {
    const val KELVIN_TO_CELSIUS = 273.15
    const val CELSIUS_SIGN = "°"

    fun applyCityWeather(body: WeatherResponse) = with(body) {
        CitiesListItem(
            id?.toString().orEmpty(),
            name.orEmpty(),
            coordinates?.lat.orEmpty(),
            coordinates?.lon.orEmpty(),
            main?.temperature?.minus(KELVIN_TO_CELSIUS)?.roundToInt()?.toString()
                ?.plus(CELSIUS_SIGN).orEmpty(),
            weather?.firstOrNull()?.description.orEmpty()
        )
    }

    fun getItemList() = listOf( //todo replace with database
        CitiesListItem(
            "1",
            "Moscow",
            "55.7504461",
            "37.6174943"
        ),
        CitiesListItem(
            "2",
            "St. Petersburg",
            "59.938732",
            "30.316229"
        )
    )
}