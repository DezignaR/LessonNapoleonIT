package com.example.lessonproject.domain

import com.example.lessonproject.di.NoteApi
import com.example.lessonproject.feature.data.Note
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val noteApi: NoteApi) {
    suspend operator fun invoke(note:Note) = noteApi.deleteNotes(listOf(note.id))
}