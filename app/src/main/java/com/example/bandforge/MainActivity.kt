package com.example.bandforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bandforge.Screens.AddBandScreen
import com.example.bandforge.Screens.BandListScreen
import com.example.bandforge.Screens.EditBandScreen

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
                        onNavigateToAdd = { navController.navigate("add_band_screen") },
                        onNavigateToEdit = { bandId ->
                            navController.navigate("edit_band_screen/$bandId")
                        }
                    )
                }

                composable("add_band_screen") {
                    AddBandScreen(
                        viewModel = bandViewModel,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }

                composable(
                    route = "edit_band_screen/{bandId}",
                    arguments = listOf(navArgument("bandId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val bandId = backStackEntry.arguments?.getInt("bandId") ?: 0
                    EditBandScreen(
                        bandId = bandId,
                        viewModel = bandViewModel,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}