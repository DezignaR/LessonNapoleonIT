package com.example.lessonproject

import android.content.Context
import androidx.annotation.LayoutRes
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) :
    MvpAppCompatFragment(contentLayoutId), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(requireContext())
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}