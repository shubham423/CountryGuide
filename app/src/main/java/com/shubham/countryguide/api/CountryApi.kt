package com.shubham.countryguide.api



import com.shubham.countryguide.models.Country
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {

    @GET("all")
    fun getCountries() : Call<List<Country>>
}