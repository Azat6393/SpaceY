package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.asteroid_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.azatberdimyradov.spacey.R

@Composable
fun AsteroidDetailsScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: AsteroidViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.astreroid_background_details),
            contentDescription = "Asteroid background",
            contentScale = ContentScale.FillWidth ,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize()
                .alpha(0.55f)
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 30.dp, bottom = 5.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.ArrowBack,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }
            state.asteroid?.let { asteroid ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = asteroid.name ?: "",
                                fontSize = 35.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            )
                            Spacer(modifier = Modifier.padding(20.dp))
                            Text(
                                text = "estimated diameter min:\n${asteroid.estimated_diameter?.kilometers?.estimated_diameter_min} km",
                                fontSize = 25.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(20.dp))
                            Text(
                                text = "estimated diameter max:\n${asteroid.estimated_diameter?.kilometers?.estimated_diameter_max} km",
                                fontSize = 25.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(20.dp))
                            Text(
                                text = "absolute magnitude: ${asteroid.absolute_magnitude_h}",
                                fontSize = 25.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(20.dp))
                            Text(
                                text = if (asteroid.is_potentially_hazardous_asteroid!!) "is potentially hazardous asteroid"
                                else "isn't potentially hazardous asteroid",
                                fontSize = 25.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(20.dp))
                            Text(
                                text = "close approach dates:",
                                fontSize = 25.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                    }
                    asteroid.close_approach_data?.let {
                        items(it) { closeApproachDate ->
                            CloseApproachDateItem(closeApproachData = closeApproachDate)
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