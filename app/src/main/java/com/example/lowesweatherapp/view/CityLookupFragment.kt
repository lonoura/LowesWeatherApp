package com.example.lowesweatherapp.view

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lowesweatherapp.R
import com.example.lowesweatherapp.databinding.FragmentCityLookupBinding
import com.example.lowesweatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityLookupFragment : Fragment() {

    private var _binding: FragmentCityLookupBinding? = null
    private lateinit var binding: FragmentCityLookupBinding

    private val weatherViewModel by activityViewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityLookupBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lookupButton.setOnClickListener {
            val cityName = binding.cityName.text.toString()
            weatherViewModel.fetchCityWeather(cityName)
            findNavController().navigate(R.id.mainWeatherFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}