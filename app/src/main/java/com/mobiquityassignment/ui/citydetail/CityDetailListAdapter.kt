package com.mobiquityassignment.ui.citydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiquityassignment.data.remote.futuredays.FutureDaysForecast
import com.mobiquityassignment.databinding.RowFutureBroadcastBinding

class CityDetailListAdapter(private var futureForecastList: List<FutureDaysForecast.Intervals>) :
    RecyclerView.Adapter<CityDetailListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RowFutureBroadcastBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(futureForecastList[position], position)
    }

    override fun getItemCount(): Int = futureForecastList.size

    fun setData(intervalList: List<FutureDaysForecast.Intervals>) {
        this.futureForecastList = intervalList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RowFutureBroadcastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(intervals: FutureDaysForecast.Intervals, pos: Int) {
            binding.tvDate.text = intervals.dt_txt
            binding.tvTemperature.text = intervals.main?.temp.toString()
            binding.tvHumidity.text = intervals.main?.humidity.toString()
            binding.tvRainChances.text = intervals.weather?.get(0)?.description
        }
    }

}