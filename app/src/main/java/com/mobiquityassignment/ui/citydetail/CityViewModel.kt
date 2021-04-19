package com.mobiquityassignment.ui.citydetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.mobiquityassignment.data.repository.BookmarkedLocationsRepository
import com.mobiquityassignment.utils.UiStateHelper
import kotlinx.coroutines.Dispatchers

class CityViewModel(var app: Application) : AndroidViewModel(app) {

    private var bookmarkedLocationsRepository = BookmarkedLocationsRepository(app)

    fun getWeatherForecast(lat: Double, long: Double) =
        liveData(Dispatchers.IO) {
            emit(UiStateHelper.loading(null))
            try {
                emit(
                    UiStateHelper.success(
                        bookmarkedLocationsRepository.getCurrentDayForecast(
                            lat,
                            long
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(UiStateHelper.error(e.localizedMessage ?: "Some error occurred!", null))
            }
        }

    fun getWeatherForecastForFuture(lat: Double, long: Double) =
        liveData(Dispatchers.IO) {
            emit(UiStateHelper.loading(null))
            try {
                emit(
                    UiStateHelper.success(
                        bookmarkedLocationsRepository.getFutureDaysForecast(
                            lat,
                            long
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(UiStateHelper.error(e.localizedMessage ?: "Some error occurred!", null))
            }
        }
}