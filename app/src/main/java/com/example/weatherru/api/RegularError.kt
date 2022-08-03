package com.example.weatherru.api

import com.google.gson.annotations.SerializedName

data class RegularError(
    @field:SerializedName("error_code")
    val code: String?,

    @field:SerializedName("error_message")
    val message: String?
)