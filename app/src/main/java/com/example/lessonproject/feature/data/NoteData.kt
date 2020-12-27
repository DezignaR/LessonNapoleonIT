package com.example.lessonproject.feature.data

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "time") var time: Date,
    @ColumnInfo(name = "complete") var complete: Boolean,
    @ColumnInfo(name = "remind") var remind: Date?
) : Parcelable

@Dao
interface NoteDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteData: NoteData)

    @Delete
    fun deleteNote(noteData: NoteData)

    @Query("SELECT * FROM noteData ORDER BY time DESC")
    fun getAll(): List<NoteData>
}

class DateConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun timestampToDate(timestamp: Long?): Date? = if (timestamp == null) null
    else Date(timestamp)
}
