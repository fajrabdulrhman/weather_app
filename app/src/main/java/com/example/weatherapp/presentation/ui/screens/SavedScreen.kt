package com.example.weatherapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.weatherapp.domain.models.WeatherResponse
import com.example.weatherapp.presentation.ui.WeatherViewModel
import com.example.weatherapp.presentation.util.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedScreen(navController: NavController) {
    val viewModel: WeatherViewModel = hiltViewModel()
    val savedWeather by viewModel.getSavedWeather().observeAsState(initial = emptyList())
    val searchState by viewModel.searchWeather.observeAsState()


    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()


    Surface(modifier = Modifier.fillMaxSize().background(
        Brush.verticalGradient(listOf(Color(0xFF3E2D8F), Color(0xB39D52AC))),
        RoundedCornerShape(16.dp)
    ) ) {
        Image(
            painter = painterResource(R.drawable.backgroundcolor),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row ( horizontalArrangement = Arrangement.spacedBy(90.dp)) {


            Box(
                modifier = Modifier
                    .offset(x = 320.dp, y = -50.dp)

                    .align(Alignment.Bottom)
                    .height(60.dp)
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(R.color.button.toInt()))
                    .clickable { showBottomSheet = true },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    tint = Color.White,
                    contentDescription = "add city button",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                // Title Row
                Row {
                    AsyncImage(
                        model = R.drawable.savebutton,
                        contentDescription = "savedCities",
                        modifier = Modifier

                            .size(25.dp)
                            .clickable {
                                navController.navigate(route = Screens.HomeScreen.route)
                            }
                    )

                    Text(
                        "   Saved Cities",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }

                Column(
                    modifier = Modifier.background(Color.Transparent)
                        .padding(10.dp)
                        .height(700.dp)
                ) {
                    LazyColumn {
                        items(savedWeather.size) { index ->
                            Spacer(Modifier.height(20.dp))
                            SavedCitesItem(city = savedWeather[index], navController)
                        }
                    }


                }
            }
        }



        // BottomSheet
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color(R.color.button.toInt())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Search city",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )

                    OutlinedTextField(
                        value = query,

                        onValueChange = {
                            query = it
                            if (it.length > 2) {
                                viewModel.searchWeather(it)
                            }
                        },
                        placeholder = { Text("Search for a city") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.Gray,
                            cursorColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.LightGray,
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.Gray
                        )
                    )


                    Spacer(Modifier.height(10.dp))


                    when (val state = searchState) {
                        is Resource.Success -> {
                            LazyColumn (modifier = Modifier, verticalArrangement =Arrangement.spacedBy(10.dp)){
                                items(state.data?.size ?: 0) { idx ->
                                    val city = state.data!![idx]
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color(R.color.button.toInt()))
                                        .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                                            .clickable {



                                                scope.launch {
                                                    // will pass the long and lat to SearchedCountryScreen then from there fetch the data fro  there

                                                    viewModel.searchWeather(city.name)
                                                    navController.navigate(Screens.SearchedCityScreen.route + "?lat=${city.lat}&lon=${city.lon}")


                                                }
                                            }
                                            .padding(14.dp)
                                    ) {
                                        Text(
                                            text = "${city.name}, ${city.country}",
                                            color = Color.White,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }

                        is Resource.Error -> {
                            Text("Error: ${state.message}", color = Color.Red)
                        }

                        is Resource.Loading -> {
                            CircularProgressIndicator()
                        }

                        else -> {}
                    }
                }
            }
        }
    }



@Composable
fun SavedCitesItem(city: WeatherResponse, navController: NavController) {
    val viewModel: WeatherViewModel = hiltViewModel()

    Box(
        modifier = Modifier
            .clickable {
                navController.navigate(Screens.DetailsScreen.route + "?cityId=${city.id}")
            }
            .width(370.dp)
            .height(190.dp)
            .border(1.dp, Color.White, RoundedCornerShape(16.dp))
            .background(Color(0x33000000), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),

        ) {
            Text(
                text = city.location.name,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth().height(80.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(40.dp))

                Text(
                    text = "${city.current.temp_c?.toInt()}°",
                    color = Color.White,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold
                )

                AsyncImage(
                    model = "https:${city.current.condition.icon}",
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(130.dp)
                )
                Spacer(modifier = Modifier.width(40.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { viewModel.deleteCounty(city) }
                )
            }

            Column(
                modifier = Modifier.height(70.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.wind),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Wind: ${city.current.wind_kph} km/h",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.hum),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Hum: ${city.current.humidity}%",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
