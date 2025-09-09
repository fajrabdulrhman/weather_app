package com.example.weatherapp.data.data_source.db.ktor.dto

import android.annotation.SuppressLint
import com.example.weatherapp.domain.models.Current
import com.example.weatherapp.domain.models.Forecast
import com.example.weatherapp.domain.models.Location
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class WeatherResponseDto (
val id: Int? = null,
val current: Current,
val forecast: Forecast,
val location: Location
)