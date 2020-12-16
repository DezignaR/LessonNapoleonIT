package com.example.lessonproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lessonproject.feature.note_general.ui.NoteGeneralFragment
import moxy.MvpAppCompatActivity

class NoteGeneralActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_general)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, NoteGeneralFragment())
            .commit()
    }

    fun navigateFragment(fragment: Fragment, tag:String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .addToBackStack(tag)
            .commit()
    }
}
