package com.example.weatherapp.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation

fun View.rotateAnimation() {
    val rotate = RotateAnimation(
        0F, 360F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
    )

    rotate.duration = 900
    rotate.repeatCount = Animation.INFINITE
    this.startAnimation(rotate)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}