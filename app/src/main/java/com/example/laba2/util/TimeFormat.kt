package com.example.laba2.util

import java.util.Locale

fun formatDuration(millis: Long): String {
    val totalSec = millis / 1000
    return String.format(Locale.getDefault(), "%d:%02d", totalSec / 60, totalSec % 60)
}