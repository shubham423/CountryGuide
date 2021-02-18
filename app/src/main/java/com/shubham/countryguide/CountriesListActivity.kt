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
    private lateinit var mAdapter: CountriesListAdapter
    private lateinit var mViewModel: CountryListViewModel
    private lateinit var mActivityBinding: ActivityCountriesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_countries_list)

        mViewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)

        mActivityBinding.viewModel = mViewModel
        mActivityBinding.lifecycleOwner = this

        initializeRecyclerView()
        initializeObservers()
    }

    private fun initializeRecyclerView() {
        mAdapter = CountriesListAdapter()
        mActivityBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initializeObservers() {
        mViewModel.fetchCountriesFromServer(false).observe(this, Observer { kt ->
            mAdapter.setData(kt)
        })
        mViewModel.mShowApiError.observe(this, Observer {
            AlertDialog.Builder(this).setMessage(it).show()
        })
        mViewModel.mShowProgressBar.observe(this, Observer { bt ->
            if (bt) {
                mActivityBinding.progressBar.visibility = View.VISIBLE
                mActivityBinding.floatingActionButton.hide()
            } else {
                mActivityBinding.progressBar.visibility = View.GONE
                mActivityBinding.floatingActionButton.show()
            }
        })
        mViewModel.mShowNetworkError.observe(this, Observer {
            AlertDialog.Builder(this).setMessage(R.string.app_no_internet_msg).show()
        })
    }


}