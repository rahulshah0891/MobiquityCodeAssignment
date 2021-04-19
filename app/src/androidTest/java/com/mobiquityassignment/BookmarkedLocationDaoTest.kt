package com.mobiquityassignment

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobiquityassignment.data.db.WeatherForecastDatabaseHelper
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkedLocationDaoTest {

    private lateinit var mDatabase: WeatherForecastDatabaseHelper

    @Before
    fun initDatabase() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherForecastDatabaseHelper::class.java
        ).build()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insert_and_select_locations() = runBlocking {
        val locations = BookmarkedLocation(1, "Baroda", -23.00, -24.00)

        mDatabase.BookmarkedLocationDao().insertLocation(locations)

        val dbLocation = mDatabase.BookmarkedLocationDao().getBookmarkedLocations().first()

        MatcherAssert.assertThat(dbLocation, CoreMatchers.equalTo(locations))
    }

    @After
    fun cleanupDatabase() {
        mDatabase.close()
    }
}