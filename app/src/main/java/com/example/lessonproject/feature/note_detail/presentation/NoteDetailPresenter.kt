package com.example.lessonproject.feature.note_detail.presentation

import com.example.lessonproject.domain.AddNoteUseCase
import com.example.lessonproject.feature.data.Note
import com.example.lessonproject.feature.data.NoteDB
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NoteDetailPresenter @Inject constructor(
    private val database: NoteDB,
    private val postNoteUseCase: AddNoteUseCase
) :
    MvpPresenter<NoteDetailView>() {

    private var noteEdit: Note? = null

    fun onAddClick(title: String, description: String, remind: Date?) {
        val note = createNote(title, description, remind)
        presenterScope.launch {
            database.noteDataDao().insertNote(note)
            postNoteUseCase(note)
            viewState.back()
        }
    }

    fun onSwitchRemindClick(makeRemind: Boolean) {
        viewState.setRemindVisible(makeRemind)
    }

    private fun formatStringToDate(
        day: String,
        month: String,
        year: String,
        hour: String,
        minutes: String,
    ): Date = SimpleDateFormat("d-MM-yyyy HH:mm").parse("$day-$month-$year $hour:$minutes")

    private fun createNote(title: String, description: String, remind: Date?): Note =
        if (noteEdit == null) Note(
            //serverId = null,
            title = title,
            description = description,
            time = Date(),
            complete = false,
            remind = remind
        ) else Note(
            id = noteEdit!!.id,
            // serverId = null,
            title = title,
            description = description,
            time = Date(),
            complete = false,
            remind = remind
        )

    fun setNoteEdit(note: Note?) {
        if (note != null) {
            noteEdit = note
            if (noteEdit!!.remind == null)
                viewState.showNoteData(noteEdit)
            else {
                onSwitchRemindClick(true)

                viewState.showNoteData(noteEdit)
            }
        }
    }

    fun createIfValid(
        day: String,
        month: String,
        year: String,
        hour: String,
        minutes: String,
        title: String,
        description: String
    ) {
        when {
            !remindDayIsCorrect(day) -> viewState.showDayIsError()
            !remindMonthIsCorrect(month) -> viewState.showMonthIsError()
            !remindYearIsCorrect(year) -> viewState.showYearIsError()
            !remindHourIsCorrect(hour) -> viewState.showHourIsError()
            !remindMinutesIsCorrect(minutes) -> viewState.showMinutesIsError()
            !remindDateIsCorrect(
                formatStringToDate(
                    day,
                    month,
                    year,
                    hour,
                    minutes
                )
            ) -> viewState.showDateIsError()
            else -> {
                onAddClick(
                    title, description, formatStringToDate(
                        day,
                        month,
                        year,
                        hour,
                        minutes
                    )
                )
            }
        }
    }

    private fun remindDateIsCorrect(remindDate: Date): Boolean = remindDate > Date()

    private fun remindDayIsCorrect(remindDay: String): Boolean {
        if (remindDay.isEmpty()) return false

        return try {
            val remind = remindDay.toInt()
            remind in 1..31

        } catch (e: Exception) {
            false
        }
    }

    private fun remindMonthIsCorrect(remindMonth: String): Boolean {
        if (remindMonth.isEmpty()) return false

        return try {
            val remind = remindMonth.toInt()
            remind in 1..12
        } catch (e: Exception) {
            false
        }
    }

    private fun remindYearIsCorrect(remindYear: String): Boolean {
        if (remindYear.isEmpty()) return false

        return try {
            val remind = remindYear.toInt()
            remind in 2020..2050
        } catch (e: Exception) {
            false
        }
    }

    private fun remindHourIsCorrect(remindHours: String): Boolean {
        if (remindHours.isEmpty()) return false

        return try {
            val remind = remindHours.toInt()
            remind in 0..23
        } catch (e: Exception) {
            false
        }
    }

    private fun remindMinutesIsCorrect(remindMinutes: String): Boolean {
        if (remindMinutes.isEmpty()) return false

        return try {
            val remind = remindMinutes.toInt()
            remind in 0..59
        } catch (e: Exception) {
            false
        }
    }
}