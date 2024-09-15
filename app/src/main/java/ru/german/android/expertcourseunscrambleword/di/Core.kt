package ru.german.android.expertcourseunscrambleword.di

import android.content.Context
import android.content.SharedPreferences

class Core(private val context: Context, val clearViewModel: ClearViewModel) {

    val sharedPreferences: SharedPreferences = context
        .getSharedPreferences("unscrambleAppData", Context.MODE_PRIVATE)
}