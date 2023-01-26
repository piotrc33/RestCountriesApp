package com.example.restcountriesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restcountriesapp.api.Country
import com.example.restcountriesapp.api.RestCountriesApi
import com.example.restcountriesapp.countriesBaseUrl
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesViewModel: ViewModel() {
    var allCountries = MutableLiveData<List<Country>>()

    init {
        viewModelScope.launch {
            loadCountries()
        }
    }

    private suspend fun loadCountries() {
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

        viewModelScope.launch {
            allCountries.value = getData(api)
        }
    }

    private suspend fun getData(api: RestCountriesApi): List<Country> {
        return withContext(Dispatchers.IO) {
            val response = async {api.getCountries()}
            val result = response.await()
            return@withContext result.sortedBy { it.name.common }
        }
    }

}
