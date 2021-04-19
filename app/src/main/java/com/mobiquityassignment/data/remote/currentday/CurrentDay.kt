package com.mobiquityassignment.data.remote.currentday

data class CurrentDay(
    var base: String? = "",
    var clouds: Clouds? = Clouds(),
    var cod: Double? = 0.0,
    var coord: Coord? = Coord(),
    var dt: Double? = 0.0,
    var id: Int? = 0,
    var main: Main? = Main(),
    var name: String? = "",
    var sys: Sys? = Sys(),
    var timezone: Double? = 0.0,
    var visibility: Int? = 0,
    var weather: List<Weather?>? = listOf(),
    var wind: Wind? = Wind()
) {
    data class Clouds(
        var all: Int? = 0
    )

    data class Coord(
        var lat: Double? = 0.0,
        var lon: Double? = 0.0
    )

    data class Main(
        var feels_like: Double? = 0.0,
        var grnd_level: Double? = 0.0,
        var humidity: Double? = 0.0,
        var pressure: Double? = 0.0,
        var sea_level: Double? = 0.0,
        var temp: Double? = 0.0,
        var temp_max: Double? = 0.0,
        var temp_min: Double? = 0.0
    )

    data class Sys(
        var sunrise: Int? = 0,
        var sunset: Int? = 0
    )

    data class Weather(
        var description: String? = "",
        var icon: String? = "",
        var id: Int? = 0,
        var main: String? = ""
    )

    data class Wind(
        var deg: Int? = 0,
        var gust: Double? = 0.0,
        var speed: Double? = 0.0
    )
}