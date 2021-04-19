package com.mobiquityassignment.network

import com.mobiquityassignment.data.remote.currentday.CurrentDay
import com.mobiquityassignment.data.remote.futuredays.FutureDaysForecast
import com.mobiquityassignment.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiInterface {

    @GET("data/2.5/weather")
    suspend fun getWeatherForecastForToday(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric"
    ): CurrentDay

    @GET("data/2.5/forecast")
    suspend fun get5DayForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric"
    ): FutureDaysForecast
}