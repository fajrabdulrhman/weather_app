package com.example.weatherapp.domain.models

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Forecast(
    val forecastday: MutableList<Forecastday>
)