package ru.german.android.expertcourseunscrambleword.result

import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.core.MyViewModel
import ru.german.android.expertcourseunscrambleword.views.stats.StatsUiState


class GameOverViewModel(
    private val clearViewModel: ClearViewModel,
    private val repository: StatsRepository
) : MyViewModel {

    fun getStatsUiState(): StatsUiState {
        val (corrects, incorrect) = repository.stats()
        return StatsUiState.Base(corrects, incorrect)
    }

    fun clear() {
        repository.clear()
        clearViewModel.clear(GameOverViewModel::class.java)
    }
}