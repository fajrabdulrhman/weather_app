package com.example.weatherapp.data.data_source.remote.ktor

import com.example.weatherapp.data.data_source.remote.ktor.dto.WeatherResponseDto

import com.example.weatherapp.data.data_source.remote.ktor.dto.SearchResponseItem
import com.example.weatherapp.presentation.util.Resource

interface KtorApi {
     suspend fun getWeather(location:String, days:Int): Resource<WeatherResponseDto>
     suspend fun searchForCountry(countryName: String): Resource<List<SearchResponseItem>>


}