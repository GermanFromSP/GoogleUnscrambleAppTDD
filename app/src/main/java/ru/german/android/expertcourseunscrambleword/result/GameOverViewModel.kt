package ru.german.android.expertcourseunscrambleword.result

import ru.german.android.expertcourseunscrambleword.views.stats.StatsUiState


class GameOverViewModel(private val repository: StatsRepository) {

    fun getStatsUiState(): StatsUiState {
        val (corrects, incorrect) = repository.stats()
        return StatsUiState.Base(corrects, incorrect)
    }
}