package com.azatberdimyradov.spacey.core.presentation.image_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.azatberdimyradov.spacey.R
import com.skydoves.landscapist.glide.GlideImage
import java.util.*

@Composable
fun ImageDetailsScreen(
    navController: NavController,
    viewModel: ImageDetailsViewModel = hiltViewModel()
) {
    val imageUrl = viewModel.imageUrlState.value
    val date = viewModel.dateState.value

    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
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
            Text(
                text = date,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            )
            Image(painter = painterResource(id = R.drawable.ic_baseline_download_24),
                contentDescription = "Download",
                modifier = Modifier
                    .clickable {
                        // download image
                        viewModel.downloadImage(
                            fileName = UUID.randomUUID().toString(),
                            imageUrl = imageUrl
                        )
                    }
            )
        }
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxSize()
                .pointerInput(
                    Unit,
                ) {
                    detectTransformGestures { _, _, zoom, rotation ->
                        scale.value *= zoom
                        rotationState.value += rotation

                    }
                }
        ) {
            GlideImage(
                imageModel = imageUrl,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(5.dp)
                    .graphicsLayer(
                        scaleX = maxOf(.5f, minOf(3f, scale.value)),
                        scaleY = maxOf(.5f, minOf(3f, scale.value)),
                        rotationZ = rotationState.value
                    ),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                },
                failure = {
                    Text(text = "Image request failed", color = Color.White)
                }
            )
        }
    }
}