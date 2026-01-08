package com.example.demonstrator3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demonstrator3.Screens.AddBandScreen
import com.example.demonstrator3.Screens.BandListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bandViewModel: BandViewModel = viewModel(factory = BandViewModel.Factory)

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "band_list_screen") {
                composable("band_list_screen") {
                    BandListScreen(
                        viewModel = bandViewModel,
                        onNavigateToAdd = { navController.navigate("add_band_screen") }
                    )
                }

                composable("add_band_screen") {
                    AddBandScreen(
                        viewModel = bandViewModel,
                        onNavigateBack = { navController.popBackStack() } // חזור אחורה
                    )
                }
            }
        }
    }
}