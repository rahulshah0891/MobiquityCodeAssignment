package com.mobiquityassignment.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * bookmarked_locations table which stores all the bookmarked locations
 */
@Entity(tableName = "bookmarked_locations")
@Parcelize
data class BookmarkedLocation(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "locationId")
    var id: Long = 0,

    var locality: String = "",

    var latitude: Double = 0.0,

    var longitude: Double = 0.0

) : Parcelable