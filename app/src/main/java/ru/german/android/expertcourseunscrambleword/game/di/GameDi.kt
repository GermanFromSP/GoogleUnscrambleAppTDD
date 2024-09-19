package ru.german.android.expertcourseunscrambleword.game.di

import ru.german.android.expertcourseunscrambleword.IntCache
import ru.german.android.expertcourseunscrambleword.di.AbstractProvideViewModel
import ru.german.android.expertcourseunscrambleword.di.Core
import ru.german.android.expertcourseunscrambleword.di.Module
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.game.GameRepository
import ru.german.android.expertcourseunscrambleword.game.GameViewModel
import ru.german.android.expertcourseunscrambleword.load.data.ParseWords
import ru.german.android.expertcourseunscrambleword.load.data.Response
import ru.german.android.expertcourseunscrambleword.load.data.StringCache

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel(): GameViewModel {

        val response = Response(listOf())
        val defaultResponse = core.gson.toJson(response)

        return GameViewModel(
            clearViewModel = core.clearViewModel,
            repository = GameRepository.Base(
                corrects = IntCache.Base(core.sharedPreferences, "corrects", 0),
                incorrect = IntCache.Base(core.sharedPreferences, "incorrect", 0),
                wordCaseIndex = IntCache.Base(
                    sharedPreferences = core.sharedPreferences,
                    key = "unscrambleWordIndex",
                    defaultValue = 0
                ),
                parseWords = ParseWords.Base(core.gson),
                dataCache = StringCache.Base(
                    core.sharedPreferences,
                    "response_data",
                    defaultResponse
                )
            )
        )
    }
}

class ProvideGameViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(
    core,
    next,
    GameViewModel::class.java
) {

    override fun module(): Module<*> = GameModule(core)
}