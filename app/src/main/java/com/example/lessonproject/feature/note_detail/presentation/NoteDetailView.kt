package com.example.lessonproject.feature.note_detail.presentation

import com.example.lessonproject.Note
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface NoteDetailView : MvpView {
    @AddToEndSingle
    fun setResult(note: Note)

    @AddToEndSingle
    fun showNoteData(note: Note?)

    @AddToEndSingle
    fun setRemindVisible(makeReminder: Boolean)

    @Skip
    fun showDayIsError()

    @Skip
    fun showMonthIsError()

    @Skip
    fun showYearIsError()

    @Skip
    fun showHourIsError()

    @Skip
    fun showMinutesIsError()

    @Skip
    fun showDateIsError()
}