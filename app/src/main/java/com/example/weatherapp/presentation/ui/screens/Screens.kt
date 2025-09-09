package com.example.weatherapp.presentation.ui.screens

import androidx.compose.runtime.Composable



sealed class Screens (val route: String){
    object SplashScreen: Screens("splash_screen")
    object HomeScreen: Screens("home_screen")
    {
        fun createRoute(lat:Double,lon: Double): String {
            return "home_screen/$lat/$lon"
        }
    }
    object SearchedCityScreen: Screens("Searched_City_screen")
    object SavedCitiesScreen: Screens("Saved_Cities_screen")
    object DetailsScreen: Screens("Details_screen")
    object SearchScreen: Screens("search_screen")

    fun withArgs( vararg navArgs: String): String {
        return buildString {

            append(route)

            navArgs.forEach { args->
                append("/$args")
            }


        }
    }
}