package com.example.restcountriesapp.flags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountriesapp.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(countriesBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(RestCountriesApi::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val countries = getData(api)

            val adapter = FlagsAdapter(countries)
            binding.recyclerViewFlags.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewFlags.adapter = adapter
        }

        binding = FragmentFlagsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewFlags
        return binding.root
    }

    private suspend fun getData(api: RestCountriesApi): List<Country> {
        return withContext(Dispatchers.IO) {
            val response = async {api.getCountries()}
            val result = response.await()
            return@withContext result
        }
    }

}

