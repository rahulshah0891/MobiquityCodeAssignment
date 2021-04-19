package com.mobiquityassignment.ui.citydetail

import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobiquityassignment.R
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.databinding.FragmentCityDetailBinding
import com.mobiquityassignment.ui.base.BaseFragment
import com.mobiquityassignment.utils.Status
import com.mobiquityassignment.utils.isInternetAvailable
import com.mobiquityassignment.utils.makeGone
import com.mobiquityassignment.utils.makeVisible

class CityDetailFragment : BaseFragment<FragmentCityDetailBinding, CityViewModel>() {

    private lateinit var selectedLocation: BookmarkedLocation
    private lateinit var mAdapter: CityDetailListAdapter

    override fun setLayout(): Int = R.layout.fragment_city_detail

    override fun setViews() {
        getFragmentArguments()
        initViews()
        initObservers()
    }

    private fun getFragmentArguments() {
        selectedLocation = arguments?.let {
            CityDetailFragmentArgs.fromBundle(it).selectedLocation
        }!!
    }

    private fun initViews() {
        mAdapter = CityDetailListAdapter(emptyList())
        dataBinding.rvFuture.adapter = mAdapter
    }

    private fun initObservers() {
        viewModel.getWeatherForecast(selectedLocation.latitude, selectedLocation.longitude)
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> {
                        dataBinding.progressBar.makeVisible()
                        dataBinding.nestedScroll.makeGone()
                        dataBinding.tvError.makeGone()
                    }

                    Status.SUCCESS -> {
                        dataBinding.progressBar.makeGone()
                        dataBinding.nestedScroll.makeVisible()
                        dataBinding.tvError.makeGone()
                        dataBinding.tvLocality.text =
                            if (!it.data?.name.isNullOrEmpty()) {
                                it.data?.name
                            } else selectedLocation.locality
                        dataBinding.tvTemperature.text = it.data?.main?.temp.toString()
                        dataBinding.tvHumidity.text = it.data?.main?.humidity.toString()
                        dataBinding.tvRainChances.text = it.data?.weather?.get(0)?.description
                    }

                    Status.ERROR -> {
                        dataBinding.progressBar.makeGone()
                        dataBinding.nestedScroll.makeGone()
                        dataBinding.tvError.makeVisible()
                        dataBinding.tvError.text = getString(R.string.alert_no_data_found)
                        if(!isInternetAvailable(requireActivity()))
                            Toast.makeText(context, getString(R.string.alert_no_internet), Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            })

        viewModel.getWeatherForecastForFuture(selectedLocation.latitude, selectedLocation.longitude).observe(
            viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        mAdapter.setData(it.data?.list ?: emptyList())
                    }

                    Status.ERROR -> {
                        dataBinding.rvFuture.makeGone()
                    }
                }
            }
        )
    }
}