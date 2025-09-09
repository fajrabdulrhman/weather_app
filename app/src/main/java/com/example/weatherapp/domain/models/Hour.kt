package com.example.weatherapp.domain.models

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Hour(
    val wind_kph: Double,
    val humidity: Double,
    val condition: Condition,
    val time:String,
    val temp_c:Double
)
