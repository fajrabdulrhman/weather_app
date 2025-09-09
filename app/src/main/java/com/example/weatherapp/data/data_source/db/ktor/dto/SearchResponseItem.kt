    package com.example.weatherapp.data.data_source.db.ktor.dto

    import android.annotation.SuppressLint
    import kotlinx.serialization.Serializable
    import kotlinx.serialization.Serializer

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