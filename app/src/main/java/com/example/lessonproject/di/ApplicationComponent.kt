package com.example.lessonproject.di

import android.content.Context
import com.example.lessonproject.NoteApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidInjectors::class,
        AppModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<NoteApplication> {

    @Component.Factory
    interface Builder {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}