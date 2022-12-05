package com.example.weatherapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.adapters.ForecastAdapter
import com.example.weatherapp.data.models.ForecastResponse
import com.example.weatherapp.data.network.WeatherCallListener
import com.example.weatherapp.databinding.ActivityWeatherBinding
import com.example.weatherapp.utils.gone
import com.example.weatherapp.utils.rotateAnimation
import com.example.weatherapp.utils.visible
import com.example.weatherapp.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity(), WeatherCallListener {

    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        viewModel.weatherCallListener = this

        binding.btnRetry.setOnClickListener { viewModel.getTodayWeather() }

        binding.imgLoading.rotateAnimation()

        viewModel.weatherResp.observe(this) { weatherResponse ->
            if (weatherResponse == null) {
                binding.imgLoading.clearAnimation()
                binding.imgLoading.gone()
                binding.llErrorState.visible()
                // viewModel.getTodayWeather()
                return@observe
            }
            binding.imgLoading.clearAnimation()
            binding.imgLoading.gone()
            binding.llTempDetails.visible()

            binding.txtTemp.text = weatherResponse.main.temp.toInt().toString() + "°"
            binding.txtCity.text = weatherResponse.name
        }

        viewModel.forecastResponse.observe(this) { forecastResponse ->
            if (forecastResponse == null) {
                return@observe
            }
            val forecastList = mutableListOf<ForecastResponse.ForecastData>()
            for (i in forecastResponse.list.indices) {
                if (forecastResponse.list[i].dt_txt.contains("12:00:00", true)){
                    forecastList.add(forecastResponse.list[i])
                }
            }
            binding.rcyForecast.also { rcy ->
                rcy.layoutManager = LinearLayoutManager(this)
                rcy.adapter = ForecastAdapter(this, forecastList)
            }
            binding.cardForecast.visible()
            binding.cardForecast.startAnimation(AnimationUtils.loadAnimation(this@WeatherActivity, R.anim.slide_up))

        }


    }

    override fun onWeatherCallStarted() {
        binding.llErrorState.gone()
        binding.imgLoading.visible()
        binding.imgLoading.rotateAnimation()
    }

    override fun onWeatherCallSuccess() {

    }

    override fun onWeatherCallFailure() {

    }


}

