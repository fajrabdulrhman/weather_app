package com.example.weatherapp.data.data_source.db.ktor

import com.example.weatherapp.data.data_source.db.ktor.dto.WeatherResponseDto
import com.example.weatherapp.data.data_source.db.ktor.dto.SearchResponseItem
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
        return try {
            val response =client.get {
                url(Constants.BASE_URL1)
                parameter("key", Constants.API_KEY)
                parameter("q",location)
                parameter("days",days)
            }.body<WeatherResponseDto>()
            Resource.Success(response)

        }
        catch (e: RedirectResponseException) {
            Resource.Error("Redirect error: ${e.response.status.description}")

        } catch (e: ServerResponseException) {
            Resource.Error("Server error: ${e.response.status.description}")
        } catch (e: ClientRequestException) {
            Resource.Error("Client error: ${e.response.status.description}")

        } catch (e: Exception) {
            Resource.Error("internet error   ${e.message}")
        }

    }

    override suspend fun searchForCountry(countryName: String): Resource<List<SearchResponseItem>> {
        return try {
            val response =client.get {
                url(Constants.BASE_URL_SEARCH)
                parameter("key", Constants.API_KEY)
                parameter("q",countryName)

            }.body<List<SearchResponseItem>>()
            Resource.Success(response)

        }
        catch (e: RedirectResponseException) {
            Resource.Error("Redirect error: ${e.response.status.description}")

        } catch (e: ServerResponseException) {
            Resource.Error("Server error: ${e.response.status.description}")
        } catch (e: ClientRequestException) {
            Resource.Error("Client error: ${e.response.status.description}")

        } catch (e: Exception) {
            Resource.Error("internet error   ${e.message}")
        }


    }
}