package com.example.weatherapp.domain.models

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Condition(
    val code: String,
    val icon: String

)