package com.example.weatherru.features.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherru.api.MainApiService
import com.example.weatherru.api.NetworkService
import com.example.weatherru.api.model.ErrorHandler
import com.example.weatherru.features.main.data.item.CitiesListItem
import com.example.weatherru.features.main.domain.MainInteractor
import com.example.weatherru.features.main.domain.MainRepository
import com.example.weatherru.features.main.mapper.MainMapper
import kotlinx.coroutines.*
import java.lang.Exception

open class MainViewModel : ViewModel() {
    private val apiService = NetworkService.getRetrofit().create(MainApiService::class.java)
    private val repository = MainRepository(apiService, ErrorHandler)
    private val interactor = MainInteractor(repository, MainMapper)
    private val _citiesListItems = MutableLiveData<List<CitiesListItem>>()
    val citiesList: LiveData<List<CitiesListItem>> = _citiesListItems

    fun init() {
        val listItems = mutableListOf<CitiesListItem>()
        val cities = MainMapper.getItemList()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                for (item in cities)
                    interactor.getCityWeather(item.lat, item.lon)?.let { listItems.add(it) }
            } catch (e: Exception) {//todo handle exception
            }
            _citiesListItems.postValue(listItems)
        }
    }
}