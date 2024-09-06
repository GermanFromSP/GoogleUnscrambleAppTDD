package ru.german.android.expertcourseunscrambleword.result

import ru.german.android.expertcourseunscrambleword.views.stats.StatsUiState


class GameOverViewModel {

    fun getStatsUiState(): StatsUiState = StatsUiState.Base(2, 2)
}