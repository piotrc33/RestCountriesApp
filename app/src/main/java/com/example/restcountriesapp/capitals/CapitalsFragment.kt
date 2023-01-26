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

        // Logging interceptor
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//        // Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl(countriesBaseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client) // adding logging interceptor
//            .build()
//
//        val api = retrofit.create(RestCountriesApi::class.java)
//
//        CoroutineScope(Dispatchers.Main).launch {
//            val countries = getData(api)
//
//            val adapter = CapitalsAdapter(countries)
//            binding.recyclerView.layoutManager = LinearLayoutManager(context)
//            binding.recyclerView.adapter = adapter
//        }

        binding = FragmentCapitalsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView

        return binding.root
    }

//    private suspend fun getData(api: RestCountriesApi): List<Country> {
//        return withContext(Dispatchers.IO) {
//            val response = async { api.getCountries() } // here is the problem!!
//            val result = response.await()
//            return@withContext result!!
//        }
//    }

}