package com.noob.apps.mvvmcountries.repositories

import androidx.lifecycle.MutableLiveData
import com.shubham.countryguide.api.RetrofitInstance.Companion.api
import com.shubham.countryguide.interfaces.NetworkResponseCallback
import com.shubham.countryguide.models.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesRepository private constructor() {
    private lateinit var mCallback: NetworkResponseCallback
    private var mCountryList: MutableLiveData<List<Country>> =
        MutableLiveData<List<Country>>().apply { value = emptyList() }

    companion object {
        private var mInstance: CountriesRepository? = null
        fun getInstance(): CountriesRepository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = CountriesRepository()
                }
            }
            return mInstance!!
        }
    }


    private lateinit var mCountryCall: Call<List<Country>>

    fun getCountries(callback: NetworkResponseCallback, forceFetch : Boolean): MutableLiveData<List<Country>> {
        mCallback = callback
        if (mCountryList.value!!.isNotEmpty() && !forceFetch) {
            mCallback.onNetworkSuccess()
            return mCountryList
        }
        mCountryCall = api.getCountries()
        mCountryCall.enqueue(object : Callback<List<Country>> {

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                mCountryList.value = response.body()
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                mCountryList.value = emptyList()
                mCallback.onNetworkFailure(t)
            }

        })
        return mCountryList
    }
}