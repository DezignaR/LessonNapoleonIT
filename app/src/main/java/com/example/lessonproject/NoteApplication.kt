package com.example.lessonproject

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class NoteApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicatoinComponent
            .factory()
            .create(this)
}