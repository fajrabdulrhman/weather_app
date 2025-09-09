package com.example.weatherapp.data.repository

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import com.example.weatherapp.data.data_source.db.WeatherDatabase
import com.example.weatherapp.data.data_source.db.ktor.KtorApi
import com.example.weatherapp.data.data_source.db.ktor.dto.WeatherResponseDto

import com.example.weatherapp.data.data_source.db.ktor.dto.SearchResponseItem
import com.example.weatherapp.domain.models.WeatherResponse
import com.example.weatherapp.presentation.util.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor (
    private val db:WeatherDatabase,
   //private val weatherApi: WeatherApi,
    private val weatherApi: KtorApi

    ):WeatherRepo{
    @SuppressLint("ServiceCast")


    fun WeatherResponseDto.toEntity()= WeatherResponse(
        id = id,
        current = current,
        forecast=forecast,
        location = location
    )


    //function to get data from api
    override suspend fun getWeather(location:String, days:Int): Resource<WeatherResponseDto> {
       val dto=weatherApi.getWeather(location,days)
        val entity=dto.data?.toEntity()
        if (entity != null) {
            db.getWeatherDao().upsert(entity)
         //   Resource.Success(dto)
       }
    //    Log.d("wwwwwwwww",dto.data.toEntity().toString())

        return  dto



        }
    override suspend fun getWeatherFromSearch(location:String, days:Int): Resource<WeatherResponseDto> {
       val dto=weatherApi.getWeather(location,days)

        return  dto



    }

    override suspend fun searchWeather(countryName:String): Resource<List<SearchResponseItem>>{
//        val ser=RetrofitInstance.api.searchForCountry(API_KEY,countryName)
//        Log.d("repoo","$ser")
           return  weatherApi.searchForCountry(countryName)


    }

    override suspend fun upsert(weather: WeatherResponse): Long {

      return db.getWeatherDao().upsert(weather)
    }

//    override suspend fun upsert(weather: WeatherResponse): Long {
//         return  db.getWeatherDao().upsert(weather)
//    }

    override fun getSavedWeather()=db.getWeatherDao().getAllWeathers()

    override  fun getWeatherById(id: Int): LiveData<WeatherResponse>?{
        return db.getWeatherDao().getWeatherById(id)
    }


    override suspend fun deleteWeather(weather: WeatherResponse)=db.getWeatherDao().deleteCountry(weather)


}