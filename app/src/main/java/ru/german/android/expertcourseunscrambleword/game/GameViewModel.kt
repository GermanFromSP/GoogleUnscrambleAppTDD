package ru.german.android.expertcourseunscrambleword.game

import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.MyViewModel

class GameViewModel(
    private val clearViewModel: ClearViewModel,
    private val repository: GameRepository
) : MyViewModel {

    fun clickNext(): GameUiState {
        repository.next()
        return if (repository.isLastQuestion()) {
            repository.clear()
            clearViewModel.clear(GameViewModel::class.java)
            GameUiState.Finish
        } else {
            init()
        }
    }

    fun clickCheck(text: String): GameUiState {
        return if (repository.getOriginalWord() == text) {
            GameUiState.Correct
        } else GameUiState.Incorrect
    }

    fun clickSkip(): GameUiState {
        repository.skip()
        return if (repository.isLastQuestion()) {
            repository.clear()
            GameUiState.Finish
        } else {
            init()
        }
    }

    fun checkSufficient(text: String): GameUiState {
        return if (repository.getOriginalWord().length == text.length) {
            GameUiState.Sufficient
        } else GameUiState.Insufficient
    }

    fun init(isFirstRun: Boolean = true): GameUiState {
        return if (isFirstRun) {
            GameUiState.InitialState(repository.getUnscrambleWord())
        } else {
            GameUiState.Empty
        }
    }
}
