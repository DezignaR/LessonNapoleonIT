package com.example.lessonproject.feature.note_general.presentation

import com.example.lessonproject.feature.data.NoteData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface NoteGeneralView : MvpView {
    @AddToEndSingle
    fun showNoteDetail(note: NoteData)

    @OneExecution
    fun addNewNote()

    @AddToEndSingle
    fun setNotesInvisible(hasNotes: Boolean)

    @AddToEndSingle
    fun showNotes(notes: List<NoteData>)

    @OneExecution
    fun deleteNote(note: NoteData)
}
