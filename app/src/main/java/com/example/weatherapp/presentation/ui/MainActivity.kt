    package com.example.weatherapp.presentation.ui

    import android.content.Context
    import android.content.pm.PackageManager
    import android.location.LocationManager
    import android.os.Bundle
    import android.widget.Toast
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.activity.result.contract.ActivityResultContracts
    import androidx.activity.viewModels
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.app.ActivityCompat
    import androidx.core.content.ContextCompat
    import com.example.weatherapp.presentation.ui.screens.Navigation
    import dagger.hilt.android.AndroidEntryPoint
    import kotlin.getValue


    @AndroidEntryPoint
    class MainActivity : AppCompatActivity() {
        private val viewModel:WeatherViewModel by viewModels()
        private val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    getUserLocation()
                } else {
                    Toast.makeText(this,"Location permission denied", Toast.LENGTH_SHORT).show()
                }
            }



        override fun onResume() {
            super.onResume()
            checkPermissionsAndFetchWeather()
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            enableEdgeToEdge()
            setContent {

                Navigation(viewModel)
            }


        }
        private fun checkPermissionsAndFetchWeather() {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    getUserLocation()
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    Toast.makeText(this, "Location permission is required", Toast.LENGTH_LONG).show()
                    requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }
                else -> {
                    requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }



        private fun getUserLocation() {

            val locationManager =
              this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Toast.makeText(this, "Enable location services", Toast.LENGTH_SHORT).show()
                return
            }

            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) return

            locationManager.requestSingleUpdate(
                LocationManager.GPS_PROVIDER,
                { location ->
                    val lat = location.latitude
                    val lon = location.longitude
                    viewModel.getWeather(lat, lon)
           //         navController.navigate("${Screens.HomeScreen.route}?lat=$lat&lon=$lon")



                },
                null
            )
        }
    }



