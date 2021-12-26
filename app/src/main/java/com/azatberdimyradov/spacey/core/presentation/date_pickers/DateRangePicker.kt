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
import com.azatberdimyradov.spacey.core.dateOnInitMMMdd
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun DateRangePicker(
    activity: AppCompatActivity,
    modifier: Modifier,
    onDateSelected: (Pair<String, String>) -> Unit
) {
    val startDateString = remember { mutableStateOf(dateOnInitMMMdd().first) }
    val endDateString = remember { mutableStateOf(dateOnInitMMMdd().second) }

    val dateRangePicker =
        MaterialDatePicker
            .Builder.dateRangePicker()
            .setTitleText("Select Date")
            .setTheme(R.style.ThemeOverlay_App_MaterialCalendar)
            .build()

    dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->
        val startDate = dateSelected.first
        val endDate = dateSelected.second

        if (startDate != null && endDate != null) {
            onDateSelected(
                Pair(convertLongToTime(startDate), convertLongToTime(endDate))
            )
            startDateString.value = convertLongToDate(startDate)
            endDateString.value = convertLongToDate(endDate)
        }
    }
    Column(modifier = modifier) {
        Text(
            text = "${startDateString.value} - ${endDateString.value}",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    dateRangePicker.show(activity.supportFragmentManager, "date_range_picker")
                }
        )
    }
}

private fun convertLongToDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat(
        "MMM dd",
        Locale.getDefault()
    )
    return format.format(date)
}