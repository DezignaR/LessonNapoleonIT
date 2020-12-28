package com.example.lessonproject.feature.data

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "noteData")
data class Note(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") var id: Int = 0,
    //@ColumnInfo(name = "serverId") @SerializedName("id") var serverId:Int?,
    @ColumnInfo(name = "title") @SerializedName("title") var title: String,
    @ColumnInfo(name = "description") @SerializedName("description") var description: String,
    @ColumnInfo(name = "time") @SerializedName("created_at") var time: Date,
    @ColumnInfo(name = "complete") @SerializedName("completed") var complete: Boolean,
    @ColumnInfo(name = "remind") @SerializedName("remind_date") var remind: Date?
) : Parcelable

@Dao
interface NoteDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM noteData ORDER BY time DESC")
    fun getAll(): List<Note>
}

class DateConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun timestampToDate(timestamp: Long?): Date? = if (timestamp == null) null
    else Date(timestamp)
}
