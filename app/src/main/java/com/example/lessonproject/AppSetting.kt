package com.example.lessonproject

import com.chibatching.kotpref.KotprefModel

object AppSetting: KotprefModel() {
    var userToken by stringPref(null.toString())
}