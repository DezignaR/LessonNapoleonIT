package com.example.lessonproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*
@Parcelize
data class Note(
    var id: Int,
    var title: String,
    var description: String,
    var time: Date
) : Parcelable