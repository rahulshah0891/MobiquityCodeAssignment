package com.mobiquityassignment.ui.map

import android.location.Address
import android.location.Geocoder
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.mobiquityassignment.R
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.databinding.FragmentMapBinding
import com.mobiquityassignment.ui.base.BaseFragment
import com.mobiquityassignment.utils.Status
import com.mobiquityassignment.utils.isInternetAvailable
import com.mobiquityassignment.utils.makeGone
import com.mobiquityassignment.utils.makeVisible
import java.io.IOException


class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mMarker: Marker

    override fun setLayout(): Int = R.layout.fragment_map

    override fun setViews() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        initClickListeners()

        if (!isInternetAvailable(requireActivity()))
            Toast.makeText(context, getString(R.string.alert_no_internet), Toast.LENGTH_SHORT)
                .show()
    }

    override fun onMapReady(map: GoogleMap) {
        val ahmadabad = LatLng(23.0225, 72.5714)
        mMap = map
        updateMarker(ahmadabad)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ahmadabad))
        mMap.setOnMapClickListener {
            updateMarker(it)
        }
    }

    private fun initClickListeners() {
        dataBinding.btnBookmarkLocation.setOnClickListener {
            bookmarkLocation()
        }
    }

    private fun updateMarker(latLng: LatLng) {
        val geocode = Geocoder(activity)
        var locality = ""
        if (isInternetAvailable(requireActivity())) {
            try {
                val addressList: List<Address>? =
                    geocode.getFromLocation(latLng.latitude, latLng.longitude, 1)
                if (addressList != null && addressList.isNotEmpty()) {
                    locality = addressList[0].getAddressLine(0)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (!::mMarker.isInitialized) {
            mMarker = mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(locality)
            )
        } else {
            mMarker.position = latLng
            mMarker.title = locality
        }
    }

    private fun bookmarkLocation() {
        val bookmarkedLocation = BookmarkedLocation()
        bookmarkedLocation.locality = mMarker.title
        bookmarkedLocation.latitude = mMarker.position.latitude
        bookmarkedLocation.longitude = mMarker.position.longitude

        viewModel.bookmarkLocation(bookmarkedLocation).observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    Status.LOADING -> {
                        onLoad()
                    }
                    Status.SUCCESS -> {
                        dataBinding.progressBar.makeGone()
                        Toast.makeText(
                            activity,
                            "Location Bookmarked Successfully!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        findNavController().navigateUp()
                    }
                    Status.ERROR -> {
                        dataBinding.progressBar.makeGone()
                    }
                }
            }
        })
    }

    private fun onLoad() {
        dataBinding.progressBar.makeVisible()
    }
}