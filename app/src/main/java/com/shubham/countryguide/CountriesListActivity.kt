package com.shubham.countryguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.countryguide.adapters.CountriesListAdapter
import com.shubham.countryguide.databinding.ActivityCountriesListBinding
import com.shubham.countryguide.viewmodels.CountryListViewModel
import java.util.EnumSet.of
import androidx.lifecycle.ViewModelProviders

class CountriesListActivity : AppCompatActivity() {
    private lateinit var mActivityBinding: ActivityCountriesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_countries_list)

    }




}