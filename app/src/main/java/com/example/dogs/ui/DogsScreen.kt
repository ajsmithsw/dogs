package com.example.dogs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogs.ui.detail.BreedDetailRoute
import com.example.dogs.ui.list.BreedsListRoute
import com.example.dogs.ui.theme.DogsTheme


@Composable
fun DogsScreen(
    navController: NavHostController = rememberNavController()
) {
    DogsTheme {
        NavHost(
            navController = navController,
            startDestination = "list"
        ) {
            composable(route = "list") {
                BreedsListRoute { breed ->
                    navController.navigate("detail?breed=${breed.name}")
                }
            }

            composable(route = "detail?breed={breed}") { backStackEntry ->
                val breed = backStackEntry.arguments?.getString("breed")!!

                BreedDetailRoute(breed) {
                    navController.navigateUp()
                }
            }
        }
    }
}
