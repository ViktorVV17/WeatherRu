package com.example.weatherru.features.forecastdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherru.api.MainApiService
import com.example.weatherru.api.NetworkService
import com.example.weatherru.api.model.ErrorHandler
import com.example.weatherru.features.forecastdetails.data.item.ForecastListItem
import com.example.weatherru.features.forecastdetails.domain.ForecastInteractor
import com.example.weatherru.features.forecastdetails.domain.ForecastRepository
import com.example.weatherru.features.forecastdetails.mapper.ForecastMapper
import com.example.weatherru.features.main.data.item.CitiesListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ForecastDetailsViewModel : ViewModel() {

    private val apiService = NetworkService.getRetrofit().create(MainApiService::class.java)
    private val repository = ForecastRepository(apiService, ErrorHandler)
    private val interactor = ForecastInteractor(repository, ForecastMapper)

    private val _forecastList = MutableLiveData<List<ForecastListItem>>()
    val forecastList: LiveData<List<ForecastListItem>> = _forecastList

    fun init(item: CitiesListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _forecastList.postValue(interactor.getForecast(item.lat, item.lon))
            } catch (e: Exception) {//todo handle exception
            }
        }
    }
}