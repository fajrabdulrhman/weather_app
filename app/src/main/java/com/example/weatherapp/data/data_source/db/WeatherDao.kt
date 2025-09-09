package com.example.weatherapp.data.data_source.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.domain.models.WeatherResponse

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(weatherResponse: WeatherResponse):Long


    @Query("SELECT * FROM weather_db")
    fun getAllWeathers():  LiveData<List<WeatherResponse>>

    @Query("SELECT * FROM WEATHER_DB WHERE id = :id")
     fun getWeatherById(id:Int): LiveData<WeatherResponse>?

    @Delete
    suspend fun deleteCountry(weatherResponse: WeatherResponse)
}