package com.example.lessonproject.feature.note_detail.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import com.example.lessonproject.BaseFragment
import com.example.lessonproject.R
import com.example.lessonproject.feature.data.Note
import com.example.lessonproject.feature.note_detail.presentation.NoteDetailPresenter
import com.example.lessonproject.feature.note_detail.presentation.NoteDetailView
import com.example.lessonproject.feature.note_general.ui.NoteGeneralFragment
import kotlinx.android.synthetic.main.fragment_note_detail.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class NoteDetailFragment : BaseFragment(R.layout.fragment_note_detail), NoteDetailView {
    companion object {

        const val NOTE = "NOTE"

        fun newInstance(note: Note? = null) = NoteDetailFragment().apply {
            arguments = Bundle().apply {
                if (note != null)
                    putParcelable(NOTE, note)
            }
        }
    }

    @Inject
    lateinit var presenterProvider: Provider<NoteDetailPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            presenter.setNoteEdit(it.getParcelable(NOTE))
        }

        swRemind.setOnClickListener {
            presenter.onSwitchRemindClick(swRemind.isChecked)
        }

        ivAddNote.setOnClickListener {
            if (swRemind.isChecked)
                presenter.createIfValid(
                    etDayRemind.text.toString(),
                    etMonthRemind.text.toString(),
                    etYearRemind.text.toString(),
                    etHoursRemind.text.toString(),
                    etMinutesRemind.text.toString(),
                    etTitleNote.text.toString(),
                    etDescription.text.toString()
                )
            else presenter.onAddClick(
                etTitleNote.text.toString(),
                etDescription.text.toString(),
                null
            )
        }
    }


    override fun back() {
        setFragmentResult(
            NoteGeneralFragment.REQUEST_CODE,
            bundleOf()
        )
        parentFragmentManager.popBackStack()
    }

    override fun setRemindVisible(makeReminder: Boolean) {
        etDayRemind.isVisible = makeReminder
        etMonthRemind.isVisible = makeReminder
        etYearRemind.isVisible = makeReminder
        etHoursRemind.isVisible = makeReminder
        etMinutesRemind.isVisible = makeReminder
    }


    override fun showNoteData(note: Note?) {
        etTitleNote.setText(note?.title)
        etDescription.setText(note?.description)
    }

    override fun showDayIsError() {
        showErrorToast("День")
    }

    override fun showMonthIsError() {
        showErrorToast("Месяц")
    }

    override fun showYearIsError() {
        showErrorToast("Год")
    }

    override fun showHourIsError() {
        showErrorToast("Час")
    }

    override fun showMinutesIsError() {
        showErrorToast("Минуты")
    }

    override fun showDateIsError() {
        showErrorToast("Установлена прошедшая дата")
    }

    private fun showErrorToast(name: String) {
        Toast.makeText(requireContext(), "Ошибка в поле: $name", Toast.LENGTH_LONG).show()
    }
}