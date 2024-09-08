package ru.german.android.expertcourseunscrambleword.di

import ru.german.android.expertcourseunscrambleword.MyViewModel

interface Module<T : MyViewModel> {
    fun viewModel(): T
}