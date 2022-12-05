package com.example.weatherapp.data.network

import com.example.weatherapp.data.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface MyAPI {

    @POST("weather")
    suspend fun getWeatherToday(
        @Query("q") queryCity: String = "Bengaluru",
        @Query("APPID") API_KEY: String = "9b8cb8c7f11c077f8c4e217974d9ee40",
        @Query("units") units:String = "metric"
    ): Response<WeatherResponse>

// using hardcoded query field values here because we're not using it dynamically

}