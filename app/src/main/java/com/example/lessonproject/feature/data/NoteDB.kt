package com.example.lessonproject.feature.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(DateConverter::class)
@Database(entities = [NoteData::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract fun noteDataDao(): NoteDataDao
}