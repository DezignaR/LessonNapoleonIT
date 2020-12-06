package com.example.lessonproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    companion object {

        const val DETAIL_ACTIVITY_REQUEST_CODE = 1
    }

    lateinit var note: NoteClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        invisibleNote(false)
        llNote.setOnClickListener {
            intent = Intent(this, NoteDetailActivity::class.java)
            intent.putExtra("change_note", note)
            startActivityForResult(intent, DETAIL_ACTIVITY_REQUEST_CODE)
        }
        ivAddNewNote.setOnClickListener {
            intent = Intent(this, NoteDetailActivity::class.java)
            startActivityForResult(intent, DETAIL_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DETAIL_ACTIVITY_REQUEST_CODE && data != null) {
            note = data.extras?.getSerializable("add_new_note") as NoteClass
            invisibleNote(true)
            setData(note)
        }
    }

    private fun setData(note: NoteClass) {
        tvNoteTitle.text = note.title
        tvTime.text = dateFormatted(note.time)
    }

    private fun dateFormatted(date: Date): String =
        SimpleDateFormat("HH:mm", Locale("RU")).format(date)

    private fun invisibleNote(hasNotes: Boolean) {
        llNote.isVisible = hasNotes
        tvNoNote.isVisible = !hasNotes
    }
}