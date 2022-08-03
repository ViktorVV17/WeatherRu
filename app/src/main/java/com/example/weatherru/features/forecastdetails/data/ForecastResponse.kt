package com.example.weatherru.features.forecastdetails.data

import com.example.weatherru.features.main.data.MainInformation
import com.example.weatherru.features.main.data.WeatherItem
import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list")
    var list: List<ForecastResponseItem>?
)

data class ForecastResponseItem(
    @SerializedName("main")
    var main: MainInformation?,
    @SerializedName("weather")
    var weather: List<WeatherItem>?,
    @SerializedName("dt_txt")
    var time: String?
)