package com.example.weatherapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Current(
    val last_updated: String?,
    val temp_c: Double?,
    val is_day: Int?,
    val wind_kph:Double?,
    val humidity:Int?,
    val condition: Condition
)
