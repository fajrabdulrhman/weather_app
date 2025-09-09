//package com.example.weatherapp.fakes
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.weatherapp.data.data_source.db.ktor.dto.WeatherResponse1
//import com.example.weatherapp.domain.models.SearchResponse
//import com.example.weatherapp.domain.models.WeatherResponse
//import com.example.weatherapp.data.repository.WeatherRepo
//import com.example.weatherapp.presentation.util.Resource
//import retrofit2.Response
//import java.io.IOException
//class FakeRepo():WeatherRepo {
//
//
//     var shouldReturnNetworkError = true
//
//
//
//    private val fakeData= mutableListOf<WeatherResponse>()
//     private val observableLiveData=MutableLiveData<List<WeatherResponse>>(fakeData)
//
//
//    override suspend fun getWeather(location: String, days: Int): Resource.Success<WeatherResponse1> {
//        if (shouldReturnNetworkError) {
//            throw IOException("No internet")
//        } else {
//            return Resource.Success(FakeWeatherResponse.fakeWeatherResponse)
//        }
//
//    }
//
//    override suspend fun searchWeather(countryName: String): Resource.Success<SearchResponse> {
//        if (shouldReturnNetworkError){
//                throw IOException("No internet")
//            }
//        else {
//            return Resource.Success(FakeSearchResponse.searchResponse)
//        }
//    }
//
//    override suspend fun upsert(weather: WeatherResponse): Long {
//        fakeData.add(weather)
//        observableLiveData.postValue(fakeData)
//        return fakeData.size.toLong()
//    }
//
//    override fun getSavedWeather(): LiveData<List<WeatherResponse>> {
//        return observableLiveData
//    }
//
//    override suspend fun deleteWeather(weather: WeatherResponse) {
//
//       fakeData.remove(weather)
//       observableLiveData.postValue(fakeData)
//    }
//}