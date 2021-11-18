package com.example.lowesweatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesweatherapp.databinding.HourlyItemBinding
import com.example.lowesweatherapp.model.HourlyWeather

class HourlyWeatherAdapter(
    private val listener: (weather: HourlyWeather) -> Unit
) : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyViewHolder>() {

    private var hourlyWeather: List<HourlyWeather> = mutableListOf()
    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.loadData(hourlyWeather[position])
    }


    fun updateList(newWeather: List<HourlyWeather>) {
        val positionStart = hourlyWeather.size
        hourlyWeather = newWeather
        notifyItemRangeChanged(positionStart, newWeather.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyViewHolder {
        return HourlyViewHolder(
            HourlyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }


    override fun getItemCount(): Int {
        return hourlyWeather.size
    }


    inner class HourlyViewHolder(
        private val binding: HourlyItemBinding,
        private val listener: (weather: HourlyWeather) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadData(hourlyWeather: HourlyWeather) = with(binding) {
            root.setOnClickListener {
                listener.invoke(hourlyWeather)
            }
            this.hourlyTemp.text = hourlyWeather.main.temp.toString()
            this.hourlyClimate.text = hourlyWeather.weather[0].main
        }
    }

    companion object {
        private const val TAG = "adapter"
    }

}