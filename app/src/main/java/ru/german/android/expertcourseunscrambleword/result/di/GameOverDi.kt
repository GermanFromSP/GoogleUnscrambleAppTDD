package ru.german.android.expertcourseunscrambleword.result.di

import ru.german.android.expertcourseunscrambleword.IntCache
import ru.german.android.expertcourseunscrambleword.di.AbstractProvideViewModel
import ru.german.android.expertcourseunscrambleword.di.Core
import ru.german.android.expertcourseunscrambleword.di.Module
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.result.GameOverViewModel
import ru.german.android.expertcourseunscrambleword.result.StatsRepository

class GameOverModule(private val core: Core) : Module<GameOverViewModel> {

    override fun viewModel(): GameOverViewModel {
        return GameOverViewModel(
            clearViewModel = core.clearViewModel,
            repository = StatsRepository.Base(
                corrects = IntCache.Base(core.sharedPreferences, "corrects", 0),
                incorrect = IntCache.Base(core.sharedPreferences, "incorrect", 0)
            )
        )
    }
}

class ProvideGameOverViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, GameOverViewModel::class.java) {

    override fun module(): Module<*> = GameOverModule(core)
}