package com.example.lessonproject

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidInjectors::class])
interface ApplicationComponent : AndroidInjector<NoteApplication> {
    @Component.Factory
    interface Builder {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}