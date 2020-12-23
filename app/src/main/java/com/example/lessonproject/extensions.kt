package com.example.lessonproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View =
    LayoutInflater.from(context).inflate(layoutResId, this, false)

fun Date.dateFormatted(): String =
    SimpleDateFormat("d MMMM yyyy г. HH:mm", Locale("RU")).format(this)
