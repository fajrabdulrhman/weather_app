package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.data_source.db.ktor.dto.WeatherResponseDto

import com.example.weatherapp.data.data_source.db.ktor.dto.SearchResponseItem
import com.example.weatherapp.domain.models.WeatherResponse
import com.example.weatherapp.presentation.util.Resource

interface WeatherRepo{



    suspend fun getWeather(location:String,days:Int): Resource<WeatherResponseDto>
    suspend fun searchWeather(countryName:String): Resource<List<SearchResponseItem>>
    suspend fun upsert(weather: WeatherResponse):Long
    fun getSavedWeather(): LiveData<List<WeatherResponse>>

    fun getWeatherById(id:Int): LiveData<WeatherResponse>?

    suspend fun deleteWeather(weather: WeatherResponse)
    suspend fun getWeatherFromSearch(location:String, days:Int): Resource<WeatherResponseDto>

}