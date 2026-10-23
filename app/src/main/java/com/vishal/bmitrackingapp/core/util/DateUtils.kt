package com.vishal.bmitrackingapp.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatDate(timestamp:Long): String{
    if(timestamp <= 0L){
        return "Unknown date"
    }

    val formatter =
        SimpleDateFormat(
            "dd/MM/yyyy,hh:mm a",
            Locale.getDefault()
        )

    return formatter.format(
        Date(timestamp)
    )
}