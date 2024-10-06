package ru.german.android.expertcourseunscrambleword.di

import ru.german.android.expertcourseunscrambleword.core.MyViewModel

interface Module<T : MyViewModel> {
    fun viewModel(): T
}