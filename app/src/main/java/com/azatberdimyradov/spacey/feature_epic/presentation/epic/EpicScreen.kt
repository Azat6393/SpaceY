package com.azatberdimyradov.spacey.feature_epic.presentation.epic

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.azatberdimyradov.spacey.R
import com.azatberdimyradov.spacey.core.presentation.date_pickers.DatePicker
import com.azatberdimyradov.spacey.core.presentation.Screen
import com.azatberdimyradov.spacey.feature_epic.presentation.EpicViewModel
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun EpicScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    viewModel: EpicViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as AppCompatActivity
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 30.dp, bottom = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                )
                DatePicker(
                    activity = activity,
                    modifier = Modifier.fillMaxWidth()
                ){ date ->
                    viewModel.getData(date)
                }
            }
            LazyVerticalGrid(
                cells = GridCells.Fixed(2)
            ) {
                items(state.epic) { epic ->
                    EpicItem(epic = epic){ epic ->
                        navController.navigate(Screen.ImageDetailsScreen.route + "?image_url=${epic.image}&date=${epic.date}")
                    }
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (state.error.isNotBlank()) {
            LaunchedEffect(key1 = true) {
                scaffoldState.snackbarHostState.showSnackbar(state.error)
            }
        }
    }
}