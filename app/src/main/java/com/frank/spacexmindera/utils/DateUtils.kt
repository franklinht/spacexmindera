package com.frank.spacexmindera.utils

import java.text.SimpleDateFormat

class DateUtils {
    companion object {
        fun getDate(dateTime: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateTime)
            val df = SimpleDateFormat("MMM d yyyy")
            return df.format(date.time)
        }

        fun getTime(dateTime: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateTime)
            val df = SimpleDateFormat("h:mm a")
            return df.format(date.time)
        }
    }
}