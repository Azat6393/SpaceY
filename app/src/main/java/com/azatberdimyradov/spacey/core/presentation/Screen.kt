package com.azatberdimyradov.spacey.core.presentation

import com.azatberdimyradov.spacey.R

sealed class Screen(val route: String, val title: String) {

    object ImageDetailsScreen: Screen("ImageDetailsScreen","Image Details")
    object AsteroidDetailsScreen: Screen("AsteroidDetailsScreen","Asteroid Details")

    sealed class DrawerScreen(route: String, title: String, val icon: Int) :
        Screen(route, title) {
        object PeopleInSpaceScreen : DrawerScreen(
            "PeopleInSpaceScreen",
            "People in space",
            R.drawable.astronaut_icon
        )
        object AstronomyPicturesScreen : DrawerScreen(
            "AstronomyPicturesScreen",
            "Astronomy pictures of the day",
            R.drawable.rocket_icon
        )
        object NearEarthObjectScreen : DrawerScreen(
            "NearEarthObjectScreen",
            "Near Earth objects",
            R.drawable.planet_icon
        )
        object EpicScreen : DrawerScreen(
            "EpicScreen",
            "Earth Polychromatic Imaging Camera",
            R.drawable.earth_icon
        )
    }

    companion object{
        val screenFromDrawer = listOf(
            DrawerScreen.PeopleInSpaceScreen,
            DrawerScreen.AstronomyPicturesScreen,
            DrawerScreen.NearEarthObjectScreen,
            DrawerScreen.EpicScreen
        )
    }
}
