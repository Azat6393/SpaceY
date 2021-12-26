package com.azatberdimyradov.spacey.core.presentation.navigation_drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azatberdimyradov.spacey.core.presentation.Screen


@Composable
fun NavigationDrawer(
    onDestinationClicked: (route: String, title: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {

        Screen.screenFromDrawer.forEach { screen ->
            if (screen.title == Screen.DrawerScreen.PeopleInSpaceScreen.title) {
                NavigationItem(resId = screen.icon, text = screen.title, topPadding = 145.dp) {
                    onDestinationClicked(screen.route, screen.title)
                }
            } else {
                NavigationItem(resId = screen.icon, text = screen.title) {
                    onDestinationClicked(screen.route, screen.title)
                }
            }
        }
    }
}