package com.example.lessonproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lessonproject.NoteActivity.Companion.DETAIL_ACTIVITY_REQUEST_CODE
import kotlinx.android.synthetic.main.activity_note_detail.*
import java.util.*

class NoteDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        if (intent.extras?.getSerializable("change_note") != null) {
            val noteChange = intent.extras?.getSerializable("change_note") as NoteClass
            setWhenChange(noteChange)
        }

        ivAddNote.setOnClickListener {
            val note = setData()
            intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("add_new_note", note)
            setResult(DETAIL_ACTIVITY_REQUEST_CODE, intent)
            finish()
        }
    }

    private fun setWhenChange(noteClass: NoteClass) {
        etTitleNote.setText(noteClass.title)
        etDescription.setText(noteClass.description)
    }

    private fun setData(): NoteClass =
        NoteClass(1, etTitleNote.text.toString(), etDescription.text.toString(), Date())
}