package com.example.weatherapp.domain.models

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Forecastday(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)