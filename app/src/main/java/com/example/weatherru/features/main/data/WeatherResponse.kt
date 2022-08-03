package com.example.weatherru.features.main.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    var coordinates: Coordinates?,
    @SerializedName("weather")
    var weather: List<WeatherItem>?,
    @SerializedName("main")
    var main: MainInformation?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
)

data class Coordinates(
    @SerializedName("lon")
    var lon: String?,
    @SerializedName("lat")
    var lat: String?,
)

data class WeatherItem(
    @SerializedName("main")
    var main: String?,
    @SerializedName("description")
    var description: String?
)

data class MainInformation(
    @SerializedName("temp")
    var temperature: Double?,
    @SerializedName("temp_min")
    var min: String?,
    @SerializedName("temp_max")
    var max: String?
)