package com.example.lessonproject.domain

import com.example.lessonproject.di.NoteApi
import com.example.lessonproject.feature.data.Note
import javax.inject.Inject


class GetNotesUseCase @Inject constructor(
    private val noteApi: NoteApi
) {
    suspend operator fun invoke(): List<Note> = noteApi.getNotes()
}