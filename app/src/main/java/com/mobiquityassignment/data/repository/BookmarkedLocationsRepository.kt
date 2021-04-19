package com.mobiquityassignment.data.repository

import android.app.Application
import com.mobiquityassignment.data.db.WeatherForecastDatabaseHelper
import com.mobiquityassignment.data.db.dao.BookmarkedLocationDao
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.network.RestApiClient
import com.mobiquityassignment.network.RestApiInterface

/**
 * This repository class contains all the operations performed on bookmarked_locations table.
 */
class BookmarkedLocationsRepository(app: Application) {
    private lateinit var bookmarkedLocationDao: BookmarkedLocationDao
    private lateinit var apiInterface: RestApiInterface

    init {
        val db = WeatherForecastDatabaseHelper.getDatabase(app)
        db?.let {
            bookmarkedLocationDao = it.BookmarkedLocationDao()
        }
        apiInterface = RestApiClient.getService
    }

    //Insert bookmarked location
    suspend fun insertLocation(bookmarkedLocation: BookmarkedLocation) =
        bookmarkedLocationDao.insertLocation(bookmarkedLocation)

    //Get bookmarked location
    suspend fun getBookmarkedLocations() = bookmarkedLocationDao.getBookmarkedLocations()

    //Get current day forecast
    suspend fun getCurrentDayForecast(lat: Double, long: Double) =
        apiInterface.getWeatherForecastForToday(lat, long)

    //Get future broadcasts
    suspend fun getFutureDaysForecast(lat: Double, long: Double) =
        apiInterface.get5DayForecast(lat, long)

    //Delete bookmarked location
    suspend fun deleteBookmarkedLocation(bookmarkedLocation: BookmarkedLocation) =
        bookmarkedLocationDao.deleteBookmarkedLocation(bookmarkedLocation)
}