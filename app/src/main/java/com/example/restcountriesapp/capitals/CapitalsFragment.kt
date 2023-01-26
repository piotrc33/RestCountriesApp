package com.example.restcountriesapp.capitals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountriesapp.ViewModel.CountriesViewModel
import com.example.restcountriesapp.api.Country
import com.example.restcountriesapp.api.RestCountriesApi
import com.example.restcountriesapp.countriesBaseUrl
import com.example.restcountriesapp.databinding.FragmentCapitalsBinding
import com.example.restcountriesapp.flags.FlagsAdapter
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CapitalsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentCapitalsBinding

    private lateinit var countriesViewModel : CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        countriesViewModel = ViewModelProvider(requireActivity())[CountriesViewModel::class.java]
        countriesViewModel.allCountries.observe(viewLifecycleOwner) {countries ->
            val adapter = CapitalsAdapter(countries)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = adapter

        }

        binding = FragmentCapitalsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView

        return binding.root
    }

}