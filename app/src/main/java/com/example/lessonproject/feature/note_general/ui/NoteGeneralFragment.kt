package com.example.lessonproject.feature.note_general.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lessonproject.Note
import com.example.lessonproject.NoteGeneralActivity
import com.example.lessonproject.R
import com.example.lessonproject.feature.note_detail.ui.NoteDetailFragment
import com.example.lessonproject.feature.note_general.presentation.NoteGeneralPresenter
import com.example.lessonproject.feature.note_general.presentation.NoteGeneralView
import kotlinx.android.synthetic.main.fragment_note.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.alias.AddToEndSingle

class NoteGeneralFragment : MvpAppCompatFragment(R.layout.fragment_note), NoteGeneralView {

    companion object {

        const val DETAIL_ACTIVITY_REQUEST_CODE = "1"
    }

    lateinit var adapter: NoteGeneralAdapter
    private val presenter by moxyPresenter { NoteGeneralPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        ivAddNewNote.setOnClickListener {
            presenter.addNewNoteClick()
        }

        setFragmentResultListener(DETAIL_ACTIVITY_REQUEST_CODE) { requestKey, bundle ->
            if (requestKey == DETAIL_ACTIVITY_REQUEST_CODE) {
                val result = bundle.getParcelable<Note>("bundleKey") as Note
                presenter.addNote(result)
            }
        }
    }

    private fun initRecyclerView() {
        generalRecycleView.layoutManager = LinearLayoutManager(context)
        adapter = NoteGeneralAdapter(::showNoteDetail, ::deleteNote)
        generalRecycleView.adapter = adapter
    }

    override fun showNoteDetail(note: Note) {
        (activity as NoteGeneralActivity).navigateFragment(
            NoteDetailFragment.newInstance(note),
            "NoteDetailFragment"
        )
    }

    override fun addNewNote() {
        (activity as NoteGeneralActivity).navigateFragment(
            NoteDetailFragment.newInstance(),
            "NoteDetailFragment"
        )
    }

    override fun setNotesInvisible(hasNotes: Boolean) {
        generalRecycleView.isVisible = !hasNotes
        tvNoNote.isVisible = hasNotes
    }

    override fun showNotes(notes: List<Note>) {
        adapter.submitList(notes)
    }

    override fun deleteNote(note: Note) {
        presenter.deleteNote(note)
    }
}