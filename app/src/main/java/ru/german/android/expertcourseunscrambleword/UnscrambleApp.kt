package ru.german.android.expertcourseunscrambleword

import android.app.Application
import android.content.Context
import ru.german.android.expertcourseunscrambleword.views.stats.StatsUiState

class UnscrambleApp : Application() {

    lateinit var viewModel: GameViewModel
    lateinit var gameOverViewModel: GameOverViewModel

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

        gameOverViewModel  = GameOverViewModel()
    }
}

class GameOverViewModel {
    val statsUiState: StatsUiState = StatsUiState.Base(1, 1)
}