package com.example.lessonproject.feature.note_general.presentation


import android.util.Log
import com.example.lessonproject.AppSetting
import com.example.lessonproject.domain.DeleteNoteUseCase
import com.example.lessonproject.domain.GetNotesUseCase
import com.example.lessonproject.feature.data.Note
import com.example.lessonproject.feature.data.NoteDB
import com.example.lessonproject.launchWithErrorHandler
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import java.util.*
import javax.inject.Inject

class NoteGeneralPresenter @Inject constructor(
    private val database: NoteDB,
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase

) : MvpPresenter<NoteGeneralView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setUserToken()
        viewState.showLoading(true)
        presenterScope.launchWithErrorHandler(block = {
            val notes = getNotesUseCase()
            viewState.showLoading(false)
            if (notes.isEmpty()) viewState.setNotesInvisible(true)
            else viewState.showNotes(notes)
        }, onError = {
            viewState.showLoading(isShow = false)
        })
    }

    private fun setUserToken() {
        if (AppSetting.userToken == "null")
            AppSetting.userToken = UUID.randomUUID().toString()
        Log.d("USER_TOKEN", AppSetting.userToken)
    }

    fun deleteNote(note: Note) {
        presenterScope.launch {
            deleteNoteUseCase(note)
            // database.noteDataDao().deleteNote(note)
            val notes = getNotesUseCase()
            viewState.showNotes(notes)
            if (notes.isEmpty())
                viewState.setNotesInvisible(true)
        }
    }

    fun showNote() {
        presenterScope.launch {
            viewState.showNotes(getNotesRetrofit())
            viewState.setNotesInvisible(false)
        }
    }

    private suspend fun getNotesRetrofit(): List<Note> = getNotesUseCase()

    private fun getNotes(): List<Note> =
        database.noteDataDao().getAll()

    fun addNewNoteClick() {
        viewState.addNewNote()
    }


}