package com.mobiquityassignment.utils

import android.view.View

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInVisible(){
    visibility = View.INVISIBLE
}

fun View.makeGone(){
    visibility = View.GONE
}