package com.mobiquityassignment.utils

class UiStateHelper<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): UiStateHelper<T> {
            return UiStateHelper(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): UiStateHelper<T> {
            return UiStateHelper(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): UiStateHelper<T> {
            return UiStateHelper(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}