package com.example.lessonproject.feature.note_general.presentation

import com.example.lessonproject.feature.data.Note
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface NoteGeneralView : MvpView {
    @AddToEndSingle
    fun showNoteDetail(note: Note)

    @OneExecution
    fun addNewNote()

    @AddToEndSingle
    fun setNotesInvisible(hasNotes: Boolean)

    @AddToEndSingle
    fun showNotes(notes: List<Note>)

    @AddToEndSingle
    fun showLoading(isShow: Boolean)

    @OneExecution
    fun deleteNote(note: Note)
}
