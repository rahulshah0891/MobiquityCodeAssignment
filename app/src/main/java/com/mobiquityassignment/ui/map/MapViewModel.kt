package com.mobiquityassignment.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.data.repository.BookmarkedLocationsRepository
import com.mobiquityassignment.utils.UiStateHelper
import kotlinx.coroutines.Dispatchers

class MapViewModel(var app: Application) : AndroidViewModel(app) {

    private var bookmarkedLocationsRepository = BookmarkedLocationsRepository(app)

    fun bookmarkLocation(bookmarkedLocation: BookmarkedLocation) = liveData(Dispatchers.IO) {
        emit(UiStateHelper.loading(null))
        try {
            val rowsAffected = bookmarkedLocationsRepository.insertLocation(bookmarkedLocation)
            if (rowsAffected > 0)
                emit(
                    UiStateHelper.success(
                        bookmarkedLocationsRepository.getBookmarkedLocations()
                    )
                )
            else
                emit(UiStateHelper.error("Some error occurred!", null))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(UiStateHelper.error(e.localizedMessage ?: "Some error occurred!", null))
        }
    }
}