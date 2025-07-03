package com.example.dogs.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogs.ui.detail.BreedDetailRoute
import com.example.dogs.ui.list.BreedsListRoute
import com.example.dogs.ui.theme.DogsTheme


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DogsScreen(
    navController: NavHostController = rememberNavController()
) {
    DogsTheme {
        Surface {
            SharedTransitionLayout {

                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {
                    composable(route = "list") {
                        BreedsListRoute(
                            this@SharedTransitionLayout,
                            this@composable
                        ) { breed ->
                            navController.navigate("detail?breed=${breed.name}")
                        }
                    }

                    composable(route = "detail?breed={breed}") { backStackEntry ->
                        val breed = backStackEntry.arguments?.getString("breed")!!

                        BreedDetailRoute(
                            breed,
                            this@SharedTransitionLayout,
                            this@composable
                        ) {
                            navController.navigateUp()
                        }
                    }
                }
            }
        }
    }
}
