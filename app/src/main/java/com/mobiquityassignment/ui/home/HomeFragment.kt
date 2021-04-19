package com.mobiquityassignment.ui.home

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mobiquityassignment.R
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.databinding.FragmentHomeBinding
import com.mobiquityassignment.ui.base.BaseFragment
import com.mobiquityassignment.utils.*

/**
 * HomeFragment
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), OnItemClickListener,
    OnItemDeleteListener {

    private lateinit var mAdapter: HomeLocationListAdapter

    override fun setLayout() = R.layout.fragment_home

    override fun setViews() {
        initViews()
        getLocations()
    }

    private fun initViews() {
        dataBinding.fabAddCity.setOnClickListener {
            navigateToMapFragment()
        }
        dataBinding.tvError.setOnClickListener {
            navigateToMapFragment()
        }
        mAdapter = HomeLocationListAdapter(emptyList(), this, this)
        dataBinding.rvCityList.adapter = mAdapter

    }

    private fun navigateToMapFragment() {
        if (isInternetAvailable(requireContext()))
            findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
        else
            Toast.makeText(context, getString(R.string.alert_no_internet), Toast.LENGTH_SHORT)
                .show()
    }

    private fun getLocations() {
        viewModel.getBookmarkedLocation().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    onLoad()
                }
                Status.SUCCESS -> {
                    dataBinding.progressBar.makeGone()
                    val locationList = it.data
                    if (!locationList.isNullOrEmpty()) {
                        mAdapter.setLocationData(locationList)
                        dataBinding.tvError.makeGone()
                    } else {
                        dataBinding.rvCityList.makeGone()
                        dataBinding.tvError.makeVisible()
                    }
                }
                Status.ERROR -> {
                    dataBinding.progressBar.makeGone()
                    dataBinding.rvCityList.makeGone()
                    dataBinding.tvError.makeVisible()
                    dataBinding.tvError.makeVisible()
                }
            }
        })
    }

    private fun onLoad() {
        dataBinding.progressBar.makeVisible()
    }

    override fun onItemClick(bookmarkedLocation: BookmarkedLocation) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToCityDetailFragment(bookmarkedLocation)
        findNavController().navigate(action)
    }

    override fun onItemDelete(bookmarkedLocation: BookmarkedLocation) {
        viewModel.deleteBookmarkedLocation(bookmarkedLocation)
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> {
                        onLoad()
                    }
                    Status.SUCCESS -> {
                        dataBinding.progressBar.makeGone()
                        getLocations()
                    }
                    Status.ERROR -> {
                        dataBinding.progressBar.makeGone()
                    }
                }
            })
    }
}