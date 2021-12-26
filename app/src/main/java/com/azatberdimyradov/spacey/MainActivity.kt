package com.azatberdimyradov.spacey

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.core.presentation.image_details.ImageDetailsScreen
import com.azatberdimyradov.spacey.core.presentation.navigation_drawer.NavigationDrawer
import com.azatberdimyradov.spacey.core.presentation.Screen
import com.azatberdimyradov.spacey.feature_astronomy_pictures.presentation.astronomy_pictures.AstronomyPicturesScreen
import com.azatberdimyradov.spacey.feature_epic.presentation.epic.EpicScreen
import com.azatberdimyradov.spacey.feature_near_earth_object.presentation.asteroid_details.AsteroidDetailsScreen
import com.azatberdimyradov.spacey.feature_near_earth_object.presentation.near_earth_object.NearEarthObjectScreen
import com.azatberdimyradov.spacey.feature_people_in_space.presentation.people_in_space.PeopleInSpaceScreen
import com.azatberdimyradov.spacey.ui.theme.SpaceYTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceYTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                val drawer: @Composable () -> Unit = {
                    NavigationDrawer { route, title ->
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        navController.navigate(route = route)
                    }
                }
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        drawer()
                    },
                    drawerGesturesEnabled = true
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DrawerScreen.PeopleInSpaceScreen.route
                    ) {
                        composable(Screen.DrawerScreen.PeopleInSpaceScreen.route) {
                            PeopleInSpaceScreen(scaffoldState)
                        }
                        composable(Screen.DrawerScreen.AstronomyPicturesScreen.route) {
                            AstronomyPicturesScreen(
                                navController = navController,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Screen.DrawerScreen.NearEarthObjectScreen.route) {
                            NearEarthObjectScreen(
                                navController = navController,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Screen.DrawerScreen.EpicScreen.route) {
                            EpicScreen(navController = navController, scaffoldState = scaffoldState)
                        }
                        composable(
                            route = Screen.ImageDetailsScreen.route + "?image_url={image_url}&date={date}",
                            arguments = listOf(
                                navArgument(
                                    "image_url"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    "date"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            ImageDetailsScreen(
                                navController = navController
                            )
                        }
                        composable(
                            Screen.AsteroidDetailsScreen.route + "?asteroid_id={asteroid_id}",
                            arguments = listOf(
                                navArgument(Constants.PARAM_ASTEROID_ID) {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            AsteroidDetailsScreen(
                                navController = navController,
                                scaffoldState = scaffoldState
                            )
                        }
                    }
                }
            }
        }
    }
}