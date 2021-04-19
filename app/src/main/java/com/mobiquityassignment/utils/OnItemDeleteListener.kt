package com.mobiquityassignment.utils

import com.mobiquityassignment.data.db.entity.BookmarkedLocation

interface OnItemDeleteListener {
    fun onItemDelete(bookmarkedLocation: BookmarkedLocation)
}