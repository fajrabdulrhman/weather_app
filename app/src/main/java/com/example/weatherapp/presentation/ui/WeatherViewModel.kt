package com.example.weatherapp.presentation.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.data_source.db.ktor.dto.WeatherResponseDto

import com.example.weatherapp.domain.models.WeatherResponse
import com.example.weatherapp.data.repository.WeatherRepo
import com.example.weatherapp.data.data_source.db.ktor.dto.SearchResponseItem

import com.example.weatherapp.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    val weatherRepository: WeatherRepo) : ViewModel()

 {
     private val _gettingWeather = MutableStateFlow<Resource<WeatherResponseDto>>(Resource.Loading())
     val gettingWeather = _gettingWeather.asStateFlow()
    var searchWeather: MutableLiveData<Resource<List<SearchResponseItem>>> = MutableLiveData()
    var searchResponse: List<SearchResponseItem>? = null
    //function to executes our API call from repository
    fun getWeather(latitude:Double?,longitude:Double?) =
        viewModelScope.launch {

        val location = "$latitude,$longitude"

        safeBreakingWeatherCall(location, days = 5)
    }
     fun getWeather1(latitude:Double?,longitude:Double?) =
         viewModelScope.launch {

             val location = "$latitude,$longitude"

             safeBreakingWeatherCall1(location, days = 5)
         }


    fun searchWeather(countryName:String)=viewModelScope.launch {

      //  Log.d("fah", "$searchWeather")
        safeSearchWeatherCall(countryName)

    }




    fun saveWeather(weatherResponse: WeatherResponse)=viewModelScope.launch {
       // Log.d("saveWeatherr","$weatherResponse")
        weatherRepository.upsert(weatherResponse)
    }
    fun getSavedWeather()=weatherRepository.getSavedWeather()

     fun getWeatherById(cityId: Int)= weatherRepository.getWeatherById(cityId ?: 0)

    fun deleteCounty(weatherResponse: WeatherResponse)=viewModelScope.launch {
        weatherRepository.deleteWeather(weatherResponse)
    }

    private suspend fun safeSearchWeatherCall(searchQuery: String) {
       // searchWeather.postValue(Resource.Loading())
        try {
                val response = weatherRepository.searchWeather(searchQuery)
                searchWeather.value=response
            Log.d("serachCall","${(response)}")

            }
         catch(t: Throwable) {
            when(t) {
               // Log.e(TA,"${t}")
                is IOException -> searchWeather.postValue(Resource.Error("Network Failure"))
                else -> searchWeather.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private suspend fun safeBreakingWeatherCall(location: String,days:Int=5) {
        val response=weatherRepository.getWeather(location,days)
        _gettingWeather.value=response


        }
     private suspend fun safeBreakingWeatherCall1(location: String,days:Int=5) {
         val response=weatherRepository.getWeatherFromSearch(location,days)
         _gettingWeather.value=response


     }
    }



