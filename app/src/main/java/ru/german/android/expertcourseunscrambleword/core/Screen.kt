package ru.german.android.expertcourseunscrambleword.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(container: Int, fragmentManager: FragmentManager)

    abstract class Replace(private val fragment:Class<out Fragment>) : Screen {

        override fun show(container: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(container, newFragment())
                .commit()
        }

        protected open fun newFragment(): Fragment = fragment.getDeclaredConstructor().newInstance()
    }

}