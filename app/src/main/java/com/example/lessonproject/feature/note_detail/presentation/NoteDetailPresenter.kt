package com.example.lessonproject.feature.note_detail.presentation

import com.example.lessonproject.Note
import moxy.MvpPresenter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class NoteDetailPresenter : MvpPresenter<NoteDetailView>() {

    fun onAddClick(title: String, description: String) {
        viewState.setResult(createNote(title, description))
    }

    private fun onAddWithRemindClick(
        day: String,
        month: String,
        year: String,
        hour: String,
        minutes: String,
        title: String,
        description: String
    ): Note =
        Note(
            1,
            title,
            description,
            Date(),
            false,
            dateFormat(day, month, year, hour, minutes)
        )

    fun onSwitchRemindClick(makeRemind: Boolean) {
        viewState.setRemindVisible(makeRemind)
    }

    private fun dateFormat(
        day: String,
        month: String,
        year: String,
        hour: String,
        minutes: String,
    ): Date = SimpleDateFormat("d-MM-yyyy HH:mm").parse("$day-$month-$year $hour:$minutes")

    private fun createNote(title: String, description: String): Note =
        Note(1, title, description, Date(), false, null)

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
                dateFormat(
                    day,
                    month,
                    year,
                    hour,
                    minutes
                )
            ) -> viewState.showDateIsError()
            else -> {
                viewState.setResult(
                    onAddWithRemindClick(
                        day,
                        month,
                        year,
                        hour,
                        minutes,
                        title,
                        description
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