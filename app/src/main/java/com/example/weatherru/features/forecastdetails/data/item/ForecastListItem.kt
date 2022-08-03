package com.example.weatherru.features.forecastdetails.data.item

data class ForecastListItem(
    val id: String? = "",
    val time: String,
    val temperature: String,
    val weather: String
)