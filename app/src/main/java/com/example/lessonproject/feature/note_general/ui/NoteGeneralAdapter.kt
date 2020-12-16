package com.example.lessonproject.feature.note_general.ui

import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonproject.Note
import com.example.lessonproject.R
import com.example.lessonproject.inflate
import kotlinx.android.synthetic.main.item_note_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class NoteGeneralAdapter(
    private val showNoteDetail: (Note) -> Unit
) : RecyclerView.Adapter<NoteGeneralAdapter.NoteGeneralViewHolder>() {

    private var notes: List<Note> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteGeneralViewHolder =
        NoteGeneralViewHolder(parent.inflate(R.layout.item_note_card))

    override fun onBindViewHolder(holder: NoteGeneralViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteGeneralViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(note: Note) {
            with(itemView) {
                tvNoteTitle.text = note.title
                tvTime.text = dateFormatted(note.time)

                if (note.remind != null)
                    tvTimeRemind.text = dateFormatted(note.remind!!)

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
            }
        }
    }

    fun setData(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    private fun dateFormatted(date: Date): String =
        SimpleDateFormat("d MMMM yyyy г. HH:mm", Locale("RU")).format(date)
}