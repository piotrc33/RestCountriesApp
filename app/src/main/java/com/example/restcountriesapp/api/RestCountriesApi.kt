package com.example.restcountriesapp.api

import retrofit2.http.GET

interface RestCountriesApi {
    @GET("v2/all?fields=name,capital")
    suspend fun getCountries() : List<Country>
}