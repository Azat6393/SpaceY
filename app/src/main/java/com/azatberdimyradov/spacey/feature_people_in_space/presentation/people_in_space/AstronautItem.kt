package com.azatberdimyradov.spacey.feature_people_in_space.presentation.people_in_space

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azatberdimyradov.spacey.feature_people_in_space.domain.model.People

@Composable
fun AstronautItem(people: People, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = people.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            text = people.craft,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}