package com.example.lessonproject.di

import com.example.lessonproject.NoteGeneralActivity
import com.example.lessonproject.feature.note_detail.ui.NoteDetailFragment
import com.example.lessonproject.feature.note_general.ui.NoteGeneralFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AndroidInjectors {
    @ContributesAndroidInjector
    fun contributeNoteGeneralActivity(): NoteGeneralActivity

    @ContributesAndroidInjector
    fun contributeNoteGeneralFragment(): NoteGeneralFragment

    @ContributesAndroidInjector
    fun contributeNoteDetailFragment(): NoteDetailFragment
}