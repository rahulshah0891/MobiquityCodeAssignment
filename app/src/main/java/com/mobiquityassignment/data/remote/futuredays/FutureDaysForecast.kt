package com.mobiquityassignment.data.remote.futuredays

data class FutureDaysForecast(
    var city: City? = City(),
    var cnt: Int? = 0,
    var cod: String? = "",
    var list: List<Intervals> = listOf(),
    var message: Int? = 0
) {
    data class City(
        var coord: Coord? = Coord(),
        var country: String? = "",
        var id: Double? = 0.0,
        var name: String? = "",
        var population: Int? = 0,
        var sunrise: Double? = 0.0,
        var sunset: Double? = 0.0,
        var timezone: Double? = 0.0
    ) {
        data class Coord(
            var lat: Double? = 0.0,
            var lon: Double? = 0.0
        )
    }

    data class Intervals(
        var clouds: Clouds? = Clouds(),
        var dt: Double? = 0.0,
        var dt_txt: String? = "",
        var main: Main? = Main(),
        var pop: Double? = 0.0,
        var sys: Sys? = Sys(),
        var visibility: Double? = 0.0,
        var weather: List<Weather?>? = listOf(),
        var wind: Wind? = Wind()
    ) {
        data class Clouds(
            var all: Double? = 0.0
        )

        data class Main(
            var feels_like: Double? = 0.0,
            var grnd_level: Double? = 0.0,
            var humidity: Double? = 0.0,
            var pressure: Double? = 0.0,
            var sea_level: Double? = 0.0,
            var temp: Double? = 0.0,
            var temp_kf: Double? = 0.0,
            var temp_max: Double? = 0.0,
            var temp_min: Double? = 0.0
        )

        data class Sys(
            var pod: String? = ""
        )

        data class Weather(
            var description: String? = "",
            var icon: String? = "",
            var id: Double? = 0.0,
            var main: String? = ""
        )

        data class Wind(
            var deg: Double? = 0.0,
            var gust: Double? = 0.0,
            var speed: Double? = 0.0
        )
    }
}