package ru.german.android.expertcourseunscrambleword

class GameViewModel(private val repository: GameRepository) {

    fun clickNext(): GameUiState {
        repository.next()
        return GameUiState.InitialState(repository.getUnscrambleWord())
    }

    fun clickCheck(text: String): GameUiState {
        return if (repository.getOriginalWord() == text) {
            GameUiState.Correct
        } else GameUiState.Incorrect
    }

    fun clickSkip(): GameUiState {
        repository.next()
        return GameUiState.InitialState(repository.getUnscrambleWord())
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
