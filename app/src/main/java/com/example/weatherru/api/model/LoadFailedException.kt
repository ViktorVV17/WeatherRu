package com.example.weatherru.api.model

class LoadFailedException(val messageError: String, val resError: Int? = null) : Exception() //todo