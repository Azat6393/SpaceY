package com.azatberdimyradov.spacey.core.presentation.date_pickers

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.azatberdimyradov.spacey.R
import com.azatberdimyradov.spacey.core.convertLongToTime
import com.azatberdimyradov.spacey.core.convertLongToTimeMMMdd
import com.azatberdimyradov.spacey.core.getPreviousDay
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun DatePicker(
    activity: AppCompatActivity,
    modifier: Modifier,
    onDateSelected: (String) -> Unit
) {
    val dateString = remember { mutableStateOf(getPreviousDay()) }

    val datePicker =
        MaterialDatePicker
            .Builder.datePicker()
            .setTitleText("Select Date")
            .setTheme(R.style.ThemeOverlay_App_MaterialCalendar)
            .build()

    datePicker.addOnPositiveButtonClickListener { dateSelected ->
        val date = dateSelected
        if (date != null) {
            onDateSelected(
                convertLongToTime(date)
            )
            dateString.value = convertLongToTimeMMMdd(date)
        }
    }
    Column(modifier = modifier) {
        Text(
            text = dateString.value,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    datePicker.show(activity.supportFragmentManager, "date_range_picker")
                }
        )
    }
}