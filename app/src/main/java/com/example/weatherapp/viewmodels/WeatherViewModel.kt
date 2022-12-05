package com.example.weatherapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.data.network.WeatherCallListener
import com.example.weatherapp.data.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    var weatherCallListener: WeatherCallListener? = null

    private val _resp = MutableLiveData<WeatherResponse>()
    val weatherResp: LiveData<WeatherResponse>
        get() = _resp

    init {
        getTodayWeather()
    }

    fun getTodayWeather() {
        weatherCallListener?.onWeatherCallStarted()
        viewModelScope.launch {
            repository.getTodayWeather().let { response ->
                if (response.isSuccessful) {
                    _resp.postValue(response.body())
                    weatherCallListener?.onWeatherCallSuccess()
                } else {
                    Log.e(javaClass.simpleName, "getTodayWeather: API ERROR --- ${response.message()} ")
                    _resp.postValue(null)
                    weatherCallListener?.onWeatherCallFailure()
                }

            }
        }
    }
}