package com.mobiquityassignment.utils

import com.mobiquityassignment.data.db.entity.BookmarkedLocation

interface OnItemClickListener {
    fun onItemClick(bookmarkedLocation: BookmarkedLocation)
}