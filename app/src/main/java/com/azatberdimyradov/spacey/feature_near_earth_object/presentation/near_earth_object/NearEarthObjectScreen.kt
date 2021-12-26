package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.near_earth_object

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.azatberdimyradov.spacey.R
import com.azatberdimyradov.spacey.core.presentation.date_pickers.DateRangePicker
import com.azatberdimyradov.spacey.core.presentation.Screen
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun NearEarthObjectScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: NeoViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as AppCompatActivity
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.astreroid_background),
            contentDescription = "Asteroid background",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize()
                .alpha(0.65f)
        )
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
                DateRangePicker(
                    activity = activity,
                    modifier = Modifier.fillMaxWidth()
                ) { datePair ->
                    viewModel.getData(datePair.first, datePair.second)
                }
            }
            state.neo?.let { neoResponse ->
                Text(
                    text = "${neoResponse.element_count} Objects Near Earth",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                )
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                    items(neoResponse.near_earth_objects.neo_list) { neo ->
                        NeoItem(neo = neo) { asteroid_id ->
                            navController.navigate(Screen.AsteroidDetailsScreen.route + "?asteroid_id=$asteroid_id")
                        }
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