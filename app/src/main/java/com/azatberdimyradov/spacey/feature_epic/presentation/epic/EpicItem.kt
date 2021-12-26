package com.azatberdimyradov.spacey.feature_epic.presentation.epic

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.azatberdimyradov.spacey.core.dateToLong
import com.azatberdimyradov.spacey.feature_epic.domain.model.Epic
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun EpicItem(
    epic: Epic,
    onItemClick: (Epic) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        val date = dateToLong(epic.date)
        val y = SimpleDateFormat("yyyy").format(date)
        val m = SimpleDateFormat("MM").format(date)
        val d = SimpleDateFormat("dd").format(date)

        val imageUrl = "https://epic.gsfc.nasa.gov/archive/natural/$y/$m/$d/png/${epic.image}.png"
        GlideImage(
            imageModel = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    onItemClick(epic.copy(image = imageUrl))
                },
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