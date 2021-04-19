package com.mobiquityassignment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiquityassignment.data.db.dao.BookmarkedLocationDao
import com.mobiquityassignment.data.db.entity.BookmarkedLocation
import com.mobiquityassignment.utils.Constants
import com.mobiquityassignment.utils.Constants.DB_NAME

@Database(entities = [BookmarkedLocation::class], version = Constants.DB_VERSION)
abstract class WeatherForecastDatabaseHelper : RoomDatabase() {

    abstract fun BookmarkedLocationDao(): BookmarkedLocationDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherForecastDatabaseHelper? = null

        fun getDatabase(context: Context): WeatherForecastDatabaseHelper? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherForecastDatabaseHelper::class.java, DB_NAME
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}