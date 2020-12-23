package com.example.lessonproject.feature.note_general.presentation

import com.example.lessonproject.NoteGeneralActivity
import com.example.lessonproject.feature.data.NoteData
import moxy.MvpPresenter

class NoteGeneralPresenter : MvpPresenter<NoteGeneralView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (getNotes().isEmpty()) viewState.setNotesInvisible(true)
        else viewState.showNotes(getNotes())
    }

    fun deleteNote(note: NoteData) {
        NoteGeneralActivity.INSTANCE.database.noteDataDao().deleteNote(note)
        val notes = getNotes()
        viewState.showNotes(notes)
        if (notes.isEmpty())
            viewState.setNotesInvisible(true)
    }

    fun updateNote(note: NoteData){
        NoteGeneralActivity.INSTANCE.database.noteDataDao().updateNote(note)
        viewState.showNotes(getNotes())
    }

    fun addNote(note: NoteData) {
        NoteGeneralActivity.INSTANCE.database.noteDataDao().insertNote(note)
        viewState.showNotes(getNotes())
        viewState.setNotesInvisible(false)
    }

    private fun getNotes(): List<NoteData> =
        NoteGeneralActivity.INSTANCE.database.noteDataDao().getAll()

    fun addNewNoteClick() {
        viewState.addNewNote()
    }
}