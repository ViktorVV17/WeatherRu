package com.example.weatherru.features.main.data.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CitiesListItem(
    val id: String,
    val name: String,
    val lat: String,
    val lon: String,
    val temperature: String? = "",
    val weather: String? = ""
) : Parcelable