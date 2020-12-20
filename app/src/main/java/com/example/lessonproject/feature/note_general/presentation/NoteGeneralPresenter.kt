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

    fun deleteNote(note: Note) {
        notes.remove(note)
        viewState.showNotes(notes.sortedByDescending { it.id })
        if (notes.isEmpty())
            viewState.setNotesInvisible(true)
    }

    fun addNote(note: Note) {
        note.id = getID()
        notes.add(note)
        viewState.showNotes(notes.sortedByDescending { it.id })
        viewState.setNotesInvisible(false)
    }

    fun addNewNoteClick() {
        viewState.addNewNote()
    }

    private fun getID(): Int {
        return if (notes.isEmpty())
            1
        else
            notes[notes.size - 1].id + 1
    }
}