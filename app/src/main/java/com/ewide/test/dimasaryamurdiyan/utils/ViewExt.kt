package com.ewide.test.dimasaryamurdiyan.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide
        .with(this)
        .load(url)
        .into(this)
}

fun Context.shortToast(message : CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Double.roundTo(n : Int) : Double {
    return "%.${n}f".format(this).toDouble()
}