package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.fakes.FakeRepo
import com.example.weatherapp.data.data_source.db.WeatherDatabase
import com.example.weatherapp.domain.di.AppModule
import com.example.weatherapp.data.repository.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton


@Module
 @TestInstallIn(
     components = [SingletonComponent::class],
     replaces = [AppModule::class]
 )
object TestAppModule {

    @Provides
    @Singleton
    fun provideFakeWeatherRepo(): WeatherRepo = FakeRepo()

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}

