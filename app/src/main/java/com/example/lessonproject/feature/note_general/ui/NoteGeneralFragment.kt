package com.example.lessonproject.feature.note_general.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lessonproject.BaseFragment
import com.example.lessonproject.NoteGeneralActivity
import com.example.lessonproject.R
import com.example.lessonproject.feature.data.Note
import com.example.lessonproject.feature.note_detail.ui.NoteDetailFragment
import com.example.lessonproject.feature.note_general.presentation.NoteGeneralPresenter
import com.example.lessonproject.feature.note_general.presentation.NoteGeneralView
import kotlinx.android.synthetic.main.fragment_note.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class NoteGeneralFragment : BaseFragment(R.layout.fragment_note), NoteGeneralView {

    companion object {

        const val REQUEST_CODE = "1"
    }

    lateinit var adapter: NoteGeneralAdapter

    @Inject
    lateinit var presenterProvider: Provider<NoteGeneralPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        ivAddNewNote.setOnClickListener {
            presenter.addNewNoteClick()
        }

        setFragmentResultListener(REQUEST_CODE) { requestKey, _ ->
            if (requestKey == REQUEST_CODE) {
                presenter.showNote()
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

    override fun showLoading(isShow: Boolean) {
        loadingProgress.isVisible = isShow
    }
}