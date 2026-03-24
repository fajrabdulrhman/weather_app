package com.example.weatherapp.presentation.util

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

class Constants {
    companion object{
        const val API_KEY="b0122701210b4e63bc4111148250109"

        const val BASE_URL="https://api.weatherapi.com/v1/"
        const val BASE_URL1="https://api.weatherapi.com/v1/forecast.json"
        const val BASE_URL_SEARCH="https://api.weatherapi.com/v1/search.json"

        suspend fun <T>     safeApiCall(
            apiCall: suspend () -> T
        ): Resource<T> {
            return try {
                val result = apiCall()
                Resource.Success(result)
            } catch (e: RedirectResponseException) {
                Resource.Error("Redirect error: ${e.response.status.description}")
            } catch (e: ServerResponseException) {
                Resource.Error("Server error: ${e.response.status.description}")
            } catch (e: ClientRequestException) {
                Resource.Error("Client error: ${e.response.status.description}")
            } catch (e: Exception) {
                Resource.Error("Internet error: ${e.message}")
            }
        }
    }


    }
