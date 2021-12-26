package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.near_earth_object

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.Neo

@Composable
fun NeoItem(
    neo: Neo,
    onItemClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                neo.id?.let{
                    onItemClicked(it)
                }
            },
    ) {
        Text(
            text = neo.name!!,
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "estimated diameter min: ${neo.estimated_diameter?.kilometers?.estimated_diameter_min} km",
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = neo.close_approach_data!![0].close_approach_date_full ,
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}