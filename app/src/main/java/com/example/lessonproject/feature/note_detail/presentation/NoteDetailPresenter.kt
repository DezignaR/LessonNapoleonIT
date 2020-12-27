package com.example.lessonproject.feature.note_detail.presentation

import com.example.lessonproject.feature.data.NoteData
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.util.*

class NoteDetailPresenter : MvpPresenter<NoteDetailView>() {

    private var noteEdit: NoteData? = null

    /**
     * Добавления заметки без напоминания
     **/

    fun onAddClick(title: String, description: String) {
        viewState.setResult(createNote(title, description, null))
    }

    /**
     * Добавления заметки с напоминанем
     **/

    private fun onAddWithRemindClick(
        day: String,
        month: String,
        year: String,
        hour: String,
        minutes: String,
        title: String,
        description: String
    ): NoteData = createNote(
        title,
        description,
        formatStringToDate(day, month, year, hour, minutes)
    )

    /**
     * Сделать поле ввода напоминания видимым
     * */

    fun onSwitchRemindClick(makeRemind: Boolean) {
        viewState.setRemindVisible(makeRemind)
    }

    /**
     * Формат из Строки -> Дату
     **/

    private fun formatStringToDate(
        day: String,
        month: String,
        year: String,
        hour: String,
        minutes: String,
    ): Date = SimpleDateFormat("d-MM-yyyy HH:mm").parse("$day-$month-$year $hour:$minutes")

    /**
     * Создание новой заметки или отредактированной.
     **/

    private fun createNote(title: String, description: String, remind: Date?): NoteData =
        if (noteEdit == null) NoteData(
            title = title,
            description = description,
            time = Date(),
            complete = false,
            remind = remind
        ) else NoteData(
            id = noteEdit!!.id,
            title = title,
            description = description,
            time = Date(),
            complete = false,
            remind = remind
        )

    /**
     *Сохранения заметки для редактирования и передача в вью
     * */

    fun setNoteEdit(noteData: NoteData?) {
        if (noteData != null) {
            noteEdit = noteData
            if (noteEdit!!.remind == null)
                viewState.showNoteData(noteEdit)
            else { onSwitchRemindClick(true)

                viewState.showNoteData(noteEdit)
            }
        }
    }

    /**
     *Валидация ввода времени напоминания, и если все верно передача в фуенкцию создания
     * */

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

    /**
     * Функции валидации
     * */
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