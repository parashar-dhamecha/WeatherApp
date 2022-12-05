package com.example.weatherapp.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import java.text.SimpleDateFormat
import java.util.*

fun View.rotateAnimation() {
    val rotate = RotateAnimation(
        0F, 360F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
    )

    rotate.duration = 1200
    rotate.repeatCount = Animation.INFINITE
    this.startAnimation(rotate)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

// accepts date object and converts it to Day name string
fun getDayFromDate(dateString: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date: Date = format.parse(dateString) as Date
    val final = android.text.format.DateFormat.format("EEEE", date)
    return final.toString()
}