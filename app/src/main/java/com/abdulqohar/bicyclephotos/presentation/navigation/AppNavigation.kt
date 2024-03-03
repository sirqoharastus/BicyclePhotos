package com.abdulqohar.bicyclephotos.presentation.navigation

import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abdulqohar.bicyclephotos.domain.model.BicyclePhotoItem
import com.abdulqohar.bicyclephotos.presentation.screens.DetailsScreen
import com.abdulqohar.bicyclephotos.presentation.screens.FavouritesScreen
import com.abdulqohar.bicyclephotos.presentation.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                listOfNavItems.forEach {  navItem ->
                    NavigationBarItem(selected = currentDestination?.hierarchy?.any { it.route ==navItem.route } == true,
                        onClick = {
                                  navController.navigate(navItem.route) {
                                      popUpTo(navController.graph.findStartDestination().id) {
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                                  },
                        icon = {
                            Icon(imageVector = navItem.icon , contentDescription = null )
                        },
                        label = {
                            Text(text = navItem.label)
                        })
                }
            }
        }
    ){ paddingValues ->
        NavHost(navController = navController,
            startDestination = Screens.HOME.name,
            modifier = Modifier.padding(paddingValues)
            ) {
            composable(route = Screens.HOME.name) {
                HomeScreen() {
                    val bundle: Bundle = Bundle()

                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "imageDetails",
                        value = it
                    )
                    bundle.putParcelable("imageDetails", it)
                    navController.navigate(Screens.IMAGE_DETAILS.name)
                }
            }
            composable(route = Screens.FAVOURITE.name) {
                FavouritesScreen()
            }
            composable(route = Screens.IMAGE_DETAILS.name) {
                val bicyclePhotosItem = navController.previousBackStackEntry?.savedStateHandle?.get<BicyclePhotoItem>("imageDetails")
                DetailsScreen(bicyclePhotosItem, navController)
            }
        }

    }
}