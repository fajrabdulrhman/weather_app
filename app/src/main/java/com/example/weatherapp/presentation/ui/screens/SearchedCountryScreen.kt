package com.example.weatherapp.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.data.data_source.db.ktor.dto.WeatherResponseDto
import com.example.weatherapp.domain.models.Forecastday
import com.example.weatherapp.domain.models.Hour
import com.example.weatherapp.domain.models.WeatherResponse
import com.example.weatherapp.presentation.ui.WeatherViewModel
import com.example.weatherapp.presentation.util.Resource
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SearchedCountryScreen (lat: Double?, lon: Double?,navController: NavController){

    val viewModel: WeatherViewModel = hiltViewModel()
   viewModel.getWeather1(lat,lon)

    val weatherState by viewModel.gettingWeather.collectAsState()

    LaunchedEffect(viewModel.gettingWeather) {
        Log.d("wwwwwww","$lat,$lon")

        delay(3000)

    }

    when (weatherState) {
        is Resource.Error -> {
            val weather = (weatherState as Resource.Error<WeatherResponseDto>).data

            Log.d("HomeScreen", "error: ${(weatherState as Resource.Error).message}")
            ScreenContent2(weather,navController)
        }
        is Resource.Loading -> {
            val weather = (weatherState as Resource.Loading<WeatherResponseDto>).data
            ScreenContent2(weather,navController)
        }
        is Resource.Success -> {
            val weather = (weatherState as Resource.Success<WeatherResponseDto>).data
            ScreenContent2(weather,navController)
        }
    }
}

fun formatLocalTime2(localtime: String, shortMonth: Boolean = false): String {
    if (localtime.isEmpty()) return ""

    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH)
    val dateTime = LocalDateTime.parse(localtime, inputFormatter)

    val pattern = if (shortMonth) {
        "d MMM"
    } else {
        "d MMMM "
    }

    val outputFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)
    return dateTime.format(outputFormatter)
}

@Composable
fun ScreenContent2(weatherResponseDto: WeatherResponseDto?,navController: NavController) {

    val viewModel: WeatherViewModel = hiltViewModel()

    val weatherState by viewModel.gettingWeather.collectAsState()
    val localtime = weatherResponseDto?.location?.localtime ?: ""
    val formattedDate = remember(localtime) { formatLocalTime(localtime) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.backgroundcolor),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 35.dp),


            verticalArrangement = Arrangement.spacedBy(25.dp),

            ) {
            // 1) Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(all = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Icon(
                        painter = painterResource(R.drawable.pin1),
                        contentDescription = "location",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = weatherResponseDto?.location?.name ?: "",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Text(
                    text = "Add",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {

                            val entity = weatherResponseDto?.toEntity()

                            if (entity!=null)
                            viewModel.saveWeather(entity)



                            navController.navigate("saved_cities_screen") {
                                popUpTo("saved_cities_screen") { inclusive = true }
                            }
                        }
                )

            }




            // 2) Main Weather Card
            Box(
                modifier = Modifier
                    .height(350.dp)
                    .width(300.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                    .background(Color(0x33000000), RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Today, $formattedDate", color = Color.White, fontSize = 16.sp)

                    AsyncImage(
                        model = "https:${weatherResponseDto?.current?.condition?.icon}",
                        contentDescription = "weather icon",
                        modifier = Modifier.size(100.dp)
                    )

                    Text(
                        text = "${weatherResponseDto?.current?.temp_c}°",
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text("Precipitations", fontSize = 20.sp, color = Color.White.copy(0.8f))

                    Column (
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement =Arrangement.Center) {
                            Icon(
                                painter = painterResource(R.drawable.wind),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Text("  Wind ", color = Color.White)
                            Text(" |", color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.width(4.dp))
                            Text(text = "${weatherResponseDto?.current?.wind_kph} km/h", color = Color.White)
                        }

                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement =Arrangement.Center) {
                            Icon(
                                painter = painterResource(R.drawable.hum),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Text("    Hum", color = Color.White)
                            Text("  | ", color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.width(4.dp))
                            Text(text = "${weatherResponseDto?.current?.humidity} %" , color = Color.White)
                        }
                    }
                }
            }

            // 3) Today’s Forecast
            val todaysHours =
                weatherResponseDto?.forecast?.forecastday?.getOrNull(0)?.hour ?: emptyList()
            TodayForecast(todaysHours, formattedDate)
            Text(
                "5-Days Forecasts",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )

            // 4) 5 Days Forecast
            val forecastDays = weatherResponseDto?.forecast?.forecastday ?: emptyList()
            ForCastDays(forecastday = forecastDays)
        }
    }
}



@Composable
fun TodayForecast2(hours: List<Hour>, formattedDate: String) {
    Column(
        modifier = Modifier
            .height(160.dp)
            .width(380.dp)
            .background(
                Brush.verticalGradient(listOf(Color(0xFF3E2D8F), Color(0xB39D52AC))),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Today", color = Color.White, fontSize = 16.sp)
            Text(formattedDate, color = Color.White, fontSize = 16.sp)
        }

        Divider(
            color = Color(0xFF8E78C8),
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(hours.size) { index ->
                ForecastHourItem(hour = hours[index])
            }
        }
    }
}

@Composable
fun ForecastHourItem2(hour: Hour) {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
    val formattedHour = LocalDateTime.parse(hour.time, inputFormatter).format(outputFormatter)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("${hour.temp_c}°", color = Color.White, fontSize = 16.sp)
        AsyncImage(model = "https:${hour.condition.icon}", contentDescription = null, modifier = Modifier.size(50.dp))
        Text(formattedHour, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun ForCastDays2(forecastday: List<Forecastday>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
        items(forecastday.size) { index ->
            ForeCastDaysItem(forecastday[index])
        }
    }
}

@Composable
fun ForeCastDaysItem2(forecastday: Forecastday) {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
    val formattedDay = LocalDate.parse(forecastday.date, inputFormatter).format(outputFormatter)

    Box(
        modifier = Modifier
            .width(70.dp)
            .height(140.dp)
            .background(
                Brush.verticalGradient(listOf(Color(0xFF3E2D8F), Color(0xFF8E78C8))),
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Text("${forecastday.day.avgtemp_c}°", color = Color.White)
            AsyncImage(
                modifier = Modifier.size(60.dp),
                model = "https:${forecastday.day.condition.icon}",
                contentDescription = "icon for a day"
            )
            Text(formattedDay, color = Color.White)
        }
    }

}

fun WeatherResponseDto.toEntity()= WeatherResponse(
    id = id,
    current = current,
    forecast=forecast,
    location = location
)