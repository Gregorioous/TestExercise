package com.example.testexercise.utills

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    companion object {
        fun convertTimestampToDate(timestamp: Long): String {
            try {
                val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val netDate = Date(timestamp * 1000)
                return sdf.format(netDate)
            } catch (e: Exception) {
                return "unknown date"
            }
        }
    }
}