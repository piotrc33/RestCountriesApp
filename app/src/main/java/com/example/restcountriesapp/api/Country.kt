package com.example.restcountriesapp.api

import com.google.gson.annotations.SerializedName

data class Country(
    var name: Name,
    var capital: List<String>,
    var flag: String
)

data class Name(val common: String)
