package com.example.lowesweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lowesweatherapp.R
import com.example.lowesweatherapp.databinding.FragmentMainWeatherBinding
import com.example.lowesweatherapp.databinding.FragmentWeatherDetailsBinding
import com.example.lowesweatherapp.model.ApiResponse
import com.example.lowesweatherapp.utils.loadWeatherImage
import com.example.lowesweatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding: FragmentWeatherDetailsBinding get() = _binding!!

    private val weatherViewModel by activityViewModels<WeatherViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.selectedWeather?.let { hourlyWeather ->
            (requireActivity() as MainActivity).title = weatherViewModel.cityName
            with(binding) {
                tvCurrent.text = hourlyWeather.main?.temp?.toInt().toString()
                tvFeelsLike.text = String.format(
                    getString(R.string.feels_like_temp),
                    hourlyWeather.main.feelsLike?.toInt().toString()
                )
                tvMain.text = hourlyWeather.weather[0].main
                tvDescription.text = hourlyWeather.weather[0].description
                ivWeather.loadWeatherImage(hourlyWeather.weather[0].icon ?: "")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}