package com.example.weatherapp.presentation.ui.screens

import com.example.weatherapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay


@Composable
fun SplashScreen (navController: NavController

) {
    LaunchedEffect(key1=true) {

        delay(2000)
        navController.navigate("${Screens.HomeScreen.route}")
        {
            popUpTo("splash_screen"){inclusive=true}
        }

    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.backgroundcolor),
            contentDescription = "splash screen background color",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop  //to fill the whole screen
        )

        Column (modifier = Modifier.fillMaxSize()
             .align (Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(Modifier.height(100.dp))
            Box(modifier = Modifier, contentAlignment = Alignment.Center,)
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.weather) // حط gif في res/drawable
                        .crossfade(true)
                        .build(),
                    contentDescription = "Splash Animation",
                    modifier = Modifier.size(320.dp)
                )

            }
          //  Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(R.drawable.weatherforecasts),
                contentDescription = "text",
                modifier = Modifier.size(280.dp)
            )


        }
    }
}



