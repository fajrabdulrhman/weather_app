package com.example.weatherapp.domain.models

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class
Day(
    val avghumidity: Int,
    val avgtemp_c: Double,
    val condition: Condition,
    val maxwind_kph: Double,
    val uv: Double
)