package com.mobiquityassignment.data.db.dao

import androidx.room.*
import com.mobiquityassignment.data.db.entity.BookmarkedLocation

/**
 * This interface class contains all the operations performed on bookmarked_location Table.
 */
@Dao
interface BookmarkedLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(bookmarkedLocation: BookmarkedLocation): Long

    @Query("SELECT * FROM bookmarked_locations")
    suspend fun getBookmarkedLocations(): List<BookmarkedLocation>

    @Delete
    suspend fun deleteBookmarkedLocation(bookmarkedLocation: BookmarkedLocation)
}