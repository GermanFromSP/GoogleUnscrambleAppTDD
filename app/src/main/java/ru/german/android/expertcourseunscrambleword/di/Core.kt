package ru.german.android.expertcourseunscrambleword.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import ru.german.android.expertcourseunscrambleword.load.cache.CacheModule

class Core(context: Context, val clearViewModel: ClearViewModel) {

    var runUiTests = true
    val size = 5

    val sharedPreferences: SharedPreferences = context
        .getSharedPreferences("unscrambleAppData", Context.MODE_PRIVATE)

    val gson = Gson()

    val cacheModule: CacheModule = CacheModule.Base(context)
}