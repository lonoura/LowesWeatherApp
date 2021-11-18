package com.example.lowesweatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowesweatherapp.R
import com.example.lowesweatherapp.adapter.HourlyWeatherAdapter
import com.example.lowesweatherapp.databinding.FragmentMainWeatherBinding
import com.example.lowesweatherapp.model.HourlyWeather
import com.example.lowesweatherapp.utils.ApiState
import com.example.lowesweatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainWeatherFragment : Fragment() {

    private var _binding: FragmentMainWeatherBinding? = null
    private val binding: FragmentMainWeatherBinding get() = _binding!!
    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private val hourlyWeatherAdapter by lazy { HourlyWeatherAdapter(this@MainWeatherFragment::onWeatherClicked) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHourly.apply {
            adapter = hourlyWeatherAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        setupObservers()
    }


    private fun setupObservers() = with(weatherViewModel) {
        cityWeather.observe(viewLifecycleOwner) { state ->
            if (state is ApiState.Success) {
                (requireActivity() as MainActivity).supportActionBar?.show()
                (requireActivity() as MainActivity).title = weatherViewModel.cityName
                state.data.list?.let { handleSuccess(it) }
            }
            if (state is ApiState.Error) handleFailure(state.msg)
        }
    }

    fun handleSuccess(weatherData: List<HourlyWeather>) {
        (binding.rvHourly.adapter as HourlyWeatherAdapter).updateList(weatherData)
    }

    fun handleFailure(msg: String) {
        Log.d(TAG, "handleFailure: $msg")
    }

    private fun onWeatherClicked(weather: HourlyWeather) {
        weatherViewModel.selectedWeather = weather
        findNavController().navigate(R.id.action_weatherFragment_to_detailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "Main Weather Fragment"
    }

}