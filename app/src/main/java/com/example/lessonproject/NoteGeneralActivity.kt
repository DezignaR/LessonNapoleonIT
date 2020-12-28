package com.example.lessonproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lessonproject.feature.note_general.ui.NoteGeneralFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class NoteGeneralActivity : MvpAppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_general)
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
