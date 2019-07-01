package com.example.basemodule.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object{
        fun convertMillisToDateTime(millis: Long, dateFormatPattern: String): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = millis
            val date = calendar.time
            val dateTime: String
            try {
                dateTime = SimpleDateFormat(dateFormatPattern, Locale.ENGLISH).format(date)
            } catch (e: Exception) {
                throw RuntimeException("Exception during date parsing in convertMillisToDateTime")
            }

            return dateTime
        }
    }
}