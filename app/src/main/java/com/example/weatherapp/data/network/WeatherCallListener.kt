package com.example.weatherapp.data.network

interface WeatherCallListener {
    fun onWeatherCallStarted()
    fun onWeatherCallSuccess()
    fun onWeatherCallFailure()
}