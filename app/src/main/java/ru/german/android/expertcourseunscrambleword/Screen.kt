package ru.german.android.expertcourseunscrambleword

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(container: Int, fragmentManager: FragmentManager)

    abstract class Replace(private val fragment: Fragment) : Screen {

        override fun show(container: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit()
        }
    }
}