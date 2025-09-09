package com.example.weatherapp.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.domain.models.WeatherResponse


@Database(
    entities = [WeatherResponse::class],
    version = 1
)
@TypeConverters(Convertors::class)
 abstract class WeatherDatabase:RoomDatabase () {

    abstract fun getWeatherDao(): WeatherDao
}
