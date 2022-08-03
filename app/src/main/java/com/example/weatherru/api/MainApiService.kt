package com.example.weatherru.api

import com.example.weatherru.features.forecastdetails.data.ForecastResponse
import com.example.weatherru.features.main.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {
    @GET("geo/1.0/direct?") //todo add new city, add limit?
    suspend fun getCityLatLon(
        @Query("cityName") cityName: String,
        @Query("countryCode") countryCode: String
    ): Response<Any>

    @GET("data/2.5/weather?")
    suspend fun getCityWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String? = "77148cdb71a2b40b3be0a1aec6dc7036"//todo interceptor
    ): Response<WeatherResponse>


    @GET("data/2.5/forecast?")
    suspend fun getForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String? = "77148cdb71a2b40b3be0a1aec6dc7036"
    ): Response<ForecastResponse>
}