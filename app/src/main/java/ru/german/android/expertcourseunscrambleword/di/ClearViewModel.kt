package ru.german.android.expertcourseunscrambleword.di

import ru.german.android.expertcourseunscrambleword.MyViewModel

interface ClearViewModel {

    fun clear(viewModelClass: Class<out MyViewModel>)
}