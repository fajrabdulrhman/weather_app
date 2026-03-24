package com.example.weatherapp.data.data_source.remote.ktor

import android.util.Log
import com.example.weatherapp.data.data_source.remote.ktor.dto.WeatherResponseDto
import com.example.weatherapp.data.data_source.remote.ktor.dto.SearchResponseItem
import com.example.weatherapp.presentation.util.Constants
import com.example.weatherapp.presentation.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class KtorApiImp(
    private val client: HttpClient
): KtorApi {


    override suspend fun getWeather(
        location: String,
        days: Int
    ): Resource<WeatherResponseDto> {
        Log.d("enteeeereed", "${WeatherResponseDto}")

        return Constants.safeApiCall {
          client.get {
                url(Constants.BASE_URL1)
                parameter("key", Constants.API_KEY)
                parameter("q", location)
                parameter("days", days)
            }.body<WeatherResponseDto>()
        }
    }

    override suspend fun searchForCountry(countryName: String): Resource<List<SearchResponseItem>> {
        return Constants.safeApiCall {
            client.get {
                url(Constants.BASE_URL_SEARCH)
                parameter("key", Constants.API_KEY)
                parameter("q",countryName)

            }.body<List<SearchResponseItem>>()

            }
        }
    }







