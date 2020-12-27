package com.example.lessonproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.lessonproject.feature.data.NoteDB
import com.example.lessonproject.feature.note_general.ui.NoteGeneralFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class NoteGeneralActivity : MvpAppCompatActivity(), HasAndroidInjector {
    companion object {
        internal lateinit var INSTANCE: NoteGeneralActivity
            private set
    }

    lateinit var database: NoteDB
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_general)
        INSTANCE = this
        database = Room.databaseBuilder(
            applicationContext,
            NoteDB::class.java, "note"
        )
            .allowMainThreadQueries()
            .build()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, NoteGeneralFragment())
            .commit()
    }

    fun navigateFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(tag)
            .commit()
    }
}
