package com.azatberdimyradov.spacey.core

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

fun Float.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}

fun dateOnInit(): Pair<String, String> {
    val currentYear = SimpleDateFormat("yyyy",Locale.getDefault())
        .format(Date(System.currentTimeMillis())).toInt()
    val currentMonth = SimpleDateFormat("MM",Locale.getDefault())
        .format(Date(System.currentTimeMillis())).toInt()
    val currentDay = SimpleDateFormat("dd",Locale.getDefault())
        .format(Date(System.currentTimeMillis())).toInt()

    val endDate = "$currentYear-$currentMonth-$currentDay"
    val startDate = if (currentMonth - 1 == 0) "${currentYear - 1}-12-$currentDay"
    else "$currentYear-${currentMonth - 1}-$currentDay"
    return Pair(startDate, endDate)
}

fun dateOnInitMMMdd(): Pair<String, String> {
    val time = GregorianCalendar()
    time.time = Date()
    time.add(Calendar.MONTH, -1)
    val endDate = SimpleDateFormat("MMM dd",Locale.getDefault())
        .format(Date(System.currentTimeMillis())).toString()
    val startDate = SimpleDateFormat("MMM dd",Locale.getDefault())
        .format(time.time).toString()

    return Pair(startDate, endDate)
}

fun dateToLong(date: String): Long {
    val sdf = SimpleDateFormat("yyyy-MM-dd k:m:s",Locale.getDefault())
    return sdf.parse(date).time
}

fun convertLongToTimeMMMdd(time: Long): String{
    val date = Date(time)
    val format = SimpleDateFormat(
        "MMM dd",
        Locale.getDefault()
    )
    return format.format(date)
}

fun getPreviousDay(): String{
    val month = SimpleDateFormat("MMM", Locale.getDefault())
        .format(System.currentTimeMillis())
    val day = SimpleDateFormat("dd", Locale.getDefault())
        .format(System.currentTimeMillis()).toInt() - 1
    return "$month $day"
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    )
    return format.format(date)
}
