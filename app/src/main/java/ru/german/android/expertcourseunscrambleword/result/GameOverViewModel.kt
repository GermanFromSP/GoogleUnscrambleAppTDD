package ru.german.android.expertcourseunscrambleword.result

import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.core.MyViewModel
import ru.german.android.expertcourseunscrambleword.game.GameUiObservable
import ru.german.android.expertcourseunscrambleword.game.GameUiState
import ru.german.android.expertcourseunscrambleword.load.presentation.UiObservable
import ru.german.android.expertcourseunscrambleword.views.stats.StatsUiState


class GameOverViewModel(
    private val clearViewModel: ClearViewModel,
    observable: GameUiObservable,
    private val repository: StatsRepository
) : MyViewModel.Abstract<GameUiState>(observable){

    fun getStatsUiState(): StatsUiState {
        val (corrects, incorrect) = repository.stats()
        return StatsUiState.Base(corrects, incorrect)
    }

    fun clear() {
        repository.clear()
        clearViewModel.clear(GameOverViewModel::class.java)
    }
}