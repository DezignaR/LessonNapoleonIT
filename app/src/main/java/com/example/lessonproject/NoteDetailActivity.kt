package com.example.lessonproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lessonproject.NoteActivity.Companion.DETAIL_ACTIVITY_REQUEST_CODE
import kotlinx.android.synthetic.main.activity_note_detail.*
import java.util.*

class NoteDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val noteChange = intent?.extras?.getParcelable<Note>("change_note")
        if (noteChange != null)
            showNoteData(noteChange)


        ivAddNote.setOnClickListener {
            val note = createNote()
            intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("add_new_note", note)
            setResult(DETAIL_ACTIVITY_REQUEST_CODE, intent)
            finish()
        }
    }

    private fun showNoteData(note: Note) {
        etTitleNote.setText(note.title)
        etDescription.setText(note.description)
    }

    private fun createNote(): Note =
        Note(1, etTitleNote.text.toString(), etDescription.text.toString(), Date())
}