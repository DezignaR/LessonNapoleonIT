package com.example.lessonproject

import java.io.Serializable
import java.util.*

data class NoteClass(
    var id: Int,
    var title: String,
    var description: String,
    var time: Date
) : Serializable