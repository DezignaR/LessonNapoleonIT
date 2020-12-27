package com.example.lessonproject

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AndroidInjectors {
    @ContributesAndroidInjector
    fun contribute():NoteGeneralActivity
}