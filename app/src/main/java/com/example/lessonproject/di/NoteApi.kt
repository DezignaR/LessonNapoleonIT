package com.example.lessonproject.di

import com.example.lessonproject.feature.data.Note
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteApi {

    @GET("api/notes")
    suspend fun getNotes(): List<Note>

    @POST("api/notes/delete")
    suspend fun deleteNotes(@Body ids: List<Int>)

    @POST("api/notes")
    suspend fun postNotes(@Body notes: List<Note>)
}