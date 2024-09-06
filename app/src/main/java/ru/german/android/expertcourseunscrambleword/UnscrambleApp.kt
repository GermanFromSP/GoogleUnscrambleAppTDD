package ru.german.android.expertcourseunscrambleword

import android.app.Application
import android.content.Context
import ru.german.android.expertcourseunscrambleword.game.GameRepository
import ru.german.android.expertcourseunscrambleword.game.GameViewModel
import ru.german.android.expertcourseunscrambleword.result.GameOverViewModel
import ru.german.android.expertcourseunscrambleword.result.StatsRepository
import ru.german.android.expertcourseunscrambleword.views.stats.StatsUiState

class UnscrambleApp : Application() {

    //todo di improve

    lateinit var viewModel: GameViewModel
    lateinit var gameOverViewModel: GameOverViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("unscrambleAppData", Context.MODE_PRIVATE)
        val corrects = IntCache.Base(sharedPreferences, "corrects", 0)
        val incorrect = IntCache.Base(sharedPreferences, "incorrect", 0)


        viewModel = GameViewModel(
            GameRepository.Base(
               corrects = corrects,
               incorrect = incorrect,
                wordCaseIndex = IntCache.Base(
                    sharedPreferences = sharedPreferences,
                    key = "unscrambleWordIndex",
                    defaultValue = 0
                )
            )
        )

        gameOverViewModel = GameOverViewModel(
            repository = StatsRepository.Base(
                corrects = corrects,
                incorrect = incorrect
            )
        )
    }
}
