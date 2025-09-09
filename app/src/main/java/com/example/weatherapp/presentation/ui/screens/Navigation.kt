package com.example.weatherapp.presentation.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.presentation.ui.WeatherViewModel

@Composable
fun Navigation(viewModel: WeatherViewModel) {

    val navController = rememberNavController()
    NavHost (navController= navController , startDestination = Screens.SplashScreen.route)
    {
        composable(route= Screens.SplashScreen.route) {
         SplashScreen(navController)
            Log.d("navigaton","entered splash ")

        }
        composable (route= Screens.HomeScreen.route
        ){

            HomeScreen(navController=navController,viewModel)


        }
        composable (route= Screens.SavedCitiesScreen.route){
                SavedScreen(navController)
        }

        composable (route= Screens.DetailsScreen.route + "?cityId={cityId}",
            arguments = listOf(navArgument(
                name = "cityId"
            ){
                type= NavType.IntType
            })){entry->
            val cityId=entry.arguments?.getInt("cityId") ?: -1
            DetailsScreen(id = cityId,navController)
            Log.d("idddd","${cityId}")

        }


        composable(
            route = Screens.SearchedCityScreen.route + "?lat={lat}&lon={lon}",
            arguments = listOf(
                navArgument("lat") {
                    type = NavType.StringType
                },
                navArgument("lon") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull()
            val lon = backStackEntry.arguments?.getString("lon")?.toDoubleOrNull()

            SearchedCountryScreen(lat, lon,navController)
        }









    }
}