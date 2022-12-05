package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.network.MyAPI
import javax.inject.Inject

class WeatherRepository
@Inject constructor(private val myAPI: MyAPI) {

    suspend fun getTodayWeather() = myAPI.getWeatherToday()

    suspend fun getForecast() = myAPI.getForecast()
}