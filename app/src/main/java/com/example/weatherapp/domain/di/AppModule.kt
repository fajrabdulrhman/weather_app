package com.example.weatherapp.domain.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.data_source.db.WeatherDatabase
import com.example.weatherapp.data.data_source.db.ktor.KtorApi
import com.example.weatherapp.data.data_source.db.ktor.KtorApiImp
import com.example.weatherapp.data.repository.WeatherRepo
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    

    @Singleton
    @Provides
    fun provideWeatherDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,WeatherDatabase::class.java,"weather_db").build()


    @Singleton
    @Provides
    fun provideWeatherDao(
        database: WeatherDatabase
    ) =database.getWeatherDao()



    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: KtorApi,
        dao: WeatherDatabase,
    ): WeatherRepo {
        return WeatherRepository(dao, api)
    }


//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//    }
//
//    @Provides
//    @Singleton
//    fun provideWeatherApi(retrofit: Retrofit): WeatherApi
//    {
//
//           return retrofit.create(WeatherApi::class.java)
//    }
@Provides
@Singleton
fun provideKtorClient(client: HttpClient): KtorApi
{
    return KtorApiImp(client)
}

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient
    {
        return HttpClient(Android) {
            install(Logging)
            {
                level= LogLevel.ALL
            }

            install(ContentNegotiation)
            {
                json(
                    Json{
                        ignoreUnknownKeys=true
                        isLenient=true
                    }
                )
            }
        }
    }
}