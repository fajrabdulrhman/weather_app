package com.example.weatherapp.data.data_source.remote.ktor.dto

    import android.annotation.SuppressLint
    import kotlinx.serialization.Serializable

    @SuppressLint("UnsafeOptInUsageError")
    @Serializable
    data class SearchResponseItem(
        val country: String,
        val id: Int,
        val lat: Double,
        val lon: Double,
        val name: String,
        val region: String,
        val url: String
    )