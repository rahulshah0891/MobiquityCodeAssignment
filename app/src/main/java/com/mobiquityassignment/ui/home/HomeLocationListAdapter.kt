package com.mobiquityassignment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiquityassignment.R
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.databinding.RowBookmarkedLocationsBinding
import com.mobiquityassignment.utils.OnItemClickListener
import com.mobiquityassignment.utils.OnItemDeleteListener

class HomeLocationListAdapter(
    private var locationList: List<BookmarkedLocation>,
    val onItemClickListener: OnItemClickListener,
    val onItemDeleteListener: OnItemDeleteListener
) :
    RecyclerView.Adapter<HomeLocationListAdapter.ViewHolder>() {

    //private val locationList: MutableList<BookmarkedLocation> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RowBookmarkedLocationsBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locationList[position], position)
    }

    override fun getItemCount(): Int = locationList.size

    fun setLocationData(locationList: List<BookmarkedLocation>) {
        this.locationList = locationList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RowBookmarkedLocationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmarkedLocation: BookmarkedLocation, pos: Int) {
            binding.tvLocality.text = bookmarkedLocation.locality
            binding.tvLatLng.text = binding.root.context.getString(
                R.string.location,
                bookmarkedLocation.latitude,
                bookmarkedLocation.longitude
            )
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(bookmarkedLocation)
            }
            binding.ibDelete.setOnClickListener {
                onItemDeleteListener.onItemDelete(bookmarkedLocation)
            }
        }
    }

}