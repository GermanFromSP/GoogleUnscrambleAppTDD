package ru.german.android.expertcourseunscrambleword.di

import ru.german.android.expertcourseunscrambleword.core.MyViewModel

interface ClearViewModel {

    fun clear(viewModelClass: Class<out MyViewModel<*>>)
}