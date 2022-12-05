package com.example.weatherapp.data.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.models.ForecastResponse
import com.example.weatherapp.databinding.ItemForecastBinding
import com.example.weatherapp.utils.getDayFromDate
import com.example.weatherapp.utils.gone


class ForecastAdapter(private val mContext: Context, private var forecastList: List<ForecastResponse.ForecastData>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_forecast, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = forecastList[position]

        holder.itemBinding.txtForecastDay.text =  getDayFromDate(response.dt_txt)
        holder.itemBinding.txtForecastTemp.text = response.main.temp.toInt().toString() + " °C"

    }

    override fun getItemCount() = forecastList.size


    inner class ViewHolder(val itemBinding: ItemForecastBinding) : RecyclerView.ViewHolder(itemBinding.root)

}