package com.example.restcountriesapp.flags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountriesapp.R
import com.example.restcountriesapp.ViewModel.CountriesViewModel
import com.example.restcountriesapp.api.Country
import com.example.restcountriesapp.api.RestCountriesApi
import com.example.restcountriesapp.countriesBaseUrl
import com.example.restcountriesapp.databinding.FragmentFlagsBinding
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlagsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentFlagsBinding
    private lateinit var countriesViewModel : CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        countriesViewModel = ViewModelProvider(requireActivity())[CountriesViewModel::class.java]
        countriesViewModel.allCountries.observe(viewLifecycleOwner) {countries ->
            val adapter = FlagsAdapter(countries)
            binding.recyclerViewFlags.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewFlags.adapter = adapter

        }

        binding = FragmentFlagsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewFlags
        return binding.root
    }

}

