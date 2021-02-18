package com.shubham.countryguide.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.countryguide.R
import com.shubham.countryguide.adapters.CountriesListAdapter
import com.shubham.countryguide.databinding.ActivityCountriesListBinding
import com.shubham.countryguide.databinding.FragmentCountriesListBinding
import com.shubham.countryguide.viewmodels.CountryListViewModel

class CountriesListFragment : Fragment() {

    private lateinit var mAdapter: CountriesListAdapter
    private lateinit var mViewModel: CountryListViewModel
    private lateinit var mFragmentBinding :FragmentCountriesListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mFragmentBinding =
                DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_countries_list)

        mViewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)

        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this

        initializeRecyclerView()
        initializeObservers()
        return inflater.inflate(R.layout.fragment_countries_list, container, false)
    }

    private fun initializeRecyclerView() {
        mAdapter = CountriesListAdapter()
        mFragmentBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initializeObservers() {
        mViewModel.fetchCountriesFromServer(false).observe(requireActivity(), Observer { kt ->
            mAdapter.setData(kt)
        })
        mViewModel.mShowApiError.observe(requireActivity(), Observer {
            AlertDialog.Builder(requireActivity()).setMessage(it).show()
        })
        mViewModel.mShowProgressBar.observe(requireActivity(), Observer { bt ->
            if (bt) {
                mFragmentBinding.progressBar.visibility = View.VISIBLE
                mFragmentBinding.floatingActionButton.hide()
            } else {
                mFragmentBinding.progressBar.visibility = View.GONE
                mFragmentBinding.floatingActionButton.show()
            }
        })
        mViewModel.mShowNetworkError.observe(requireActivity(), Observer {
            AlertDialog.Builder(requireContext()).setMessage(R.string.app_no_internet_msg).show()
        })
    }

}