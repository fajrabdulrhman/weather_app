package com.example.weatherapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.db.WeatherDatabase
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.ui.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val  weatherRepository=WeatherRepository(WeatherDatabase(this))
//        val viewModelProviderFactory=ViewModelProviderFactory(weatherRepository)
//       viewModel=ViewModelProvider(this,viewModelProviderFactory).get(WeatherViewModel::class.java)


            // Load the initial fragment

            loadFragment(HomeFragment())


    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.homeFragment, fragment)
            .commit()
    }
    }
