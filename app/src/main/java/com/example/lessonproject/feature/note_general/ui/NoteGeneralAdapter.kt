package com.example.lessonproject.feature.note_general.ui

import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonproject.R
import com.example.lessonproject.dateFormatted
import com.example.lessonproject.feature.data.Note
import com.example.lessonproject.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note_card.view.*

class NoteGeneralAdapter(
    private val showNoteDetail: (Note) -> Unit,
    private val deleteNote: (Note) -> Unit
) : ListAdapter<Note, NoteGeneralAdapter.NoteGeneralViewHolder>(
    object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteGeneralViewHolder =
        NoteGeneralViewHolder(parent.inflate(R.layout.item_note_card))

    override fun onBindViewHolder(holder: NoteGeneralViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteGeneralViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(note: Note) {
            with(itemView) {
                tvNoteTitle.text = note.title
                tvTime.text = (note.time).dateFormatted()

                if (note.remind != null)
                    tvTimeRemind.text = (note.remind!!).dateFormatted()

                rbComplete.isChecked = note.complete
                if (note.complete) tvNoteTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                rbComplete.setOnClickListener {
                    if (!note.complete) {
                        rbComplete.isChecked = true
                        note.complete = true
                        tvNoteTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        rbComplete.isChecked = false
                        note.complete = false
                        tvNoteTitle.paintFlags = 0
                    }
                }
                cvCardNote.setOnClickListener { showNoteDetail(note) }
                ivDelete.setOnClickListener { deleteNote(note) }
            }
        }
    }
}