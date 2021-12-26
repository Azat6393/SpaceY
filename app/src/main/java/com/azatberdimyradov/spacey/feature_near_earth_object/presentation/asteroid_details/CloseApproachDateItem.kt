package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.asteroid_details

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
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.model.CloseApproachData

@Composable
fun CloseApproachDateItem(closeApproachData: CloseApproachData) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = closeApproachData.close_approach_date_full,
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "miss distance:",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "astronomical: ${closeApproachData.miss_distance.astronomical}",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "lunar: ${closeApproachData.miss_distance.lunar}",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "kilometers: ${closeApproachData.miss_distance.kilometers}",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "miles: ${closeApproachData.miss_distance.miles}",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "relative velocity:",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "kilometers per second: ${closeApproachData.relative_velocity.kilometers_per_second}",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "kilometers per hour: ${closeApproachData.relative_velocity.kilometers_per_hour}",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "miles per hour: ${closeApproachData.relative_velocity.miles_per_hour}:",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "orbiting body: ${closeApproachData.orbiting_body}",
            fontSize = 25.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(20.dp))
    }
}