package com.mobiquityassignment.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.data.repository.BookmarkedLocationsRepository
import com.mobiquityassignment.utils.UiStateHelper
import kotlinx.coroutines.Dispatchers

class HomeViewModel(var app: Application) : AndroidViewModel(app) {

    private var bookmarkedLocationsRepository = BookmarkedLocationsRepository(app)

    fun getBookmarkedLocation() = liveData(Dispatchers.IO) {
        emit(UiStateHelper.loading(null))
        try {
            emit(
                UiStateHelper.success(
                    bookmarkedLocationsRepository.getBookmarkedLocations()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(UiStateHelper.error(e.localizedMessage ?: "Some error occurred!", null))
        }
    }

    fun deleteBookmarkedLocation(bookmarkedLocation: BookmarkedLocation) =
        liveData(Dispatchers.IO) {
            emit(UiStateHelper.loading(null))
            try {
                emit(
                    UiStateHelper.success(
                        bookmarkedLocationsRepository.deleteBookmarkedLocation(
                            bookmarkedLocation
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(UiStateHelper.error(e.localizedMessage ?: "Some error occurred!", null))
            }
        }

}