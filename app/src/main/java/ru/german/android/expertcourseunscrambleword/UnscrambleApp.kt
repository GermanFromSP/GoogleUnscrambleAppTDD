package ru.german.android.expertcourseunscrambleword

import android.app.Application
import android.content.Context

class UnscrambleApp : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("unscrambleAppData", Context.MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                wordCaseIndex = IntCache.Base(
                    sharedPreferences = sharedPreferences,
                    key = "unscrambleWordIndex",
                    defaultValue = 0
                )
            )
        )
    }
}