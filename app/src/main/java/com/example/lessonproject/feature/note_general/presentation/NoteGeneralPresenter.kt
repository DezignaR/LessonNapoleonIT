package com.example.lessonproject.feature.note_general.presentation

import com.example.lessonproject.Note
import com.example.lessonproject.feature.note_general.ui.NoteGeneralFragment
import moxy.MvpPresenter

class NoteGeneralPresenter : MvpPresenter<NoteGeneralView>() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setNotesInvisible(true)
    }

    fun addNote(note: Note) {
        notes.add(note)
        viewState.showNotes(notes)
        viewState.setNotesInvisible(false)
    }

    fun addNewNoteClick() {
        viewState.addNewNote()
    }
}