package ru.german.android.expertcourseunscrambleword

class GameViewModel(private val repository: GameRepository) {

    fun clickNext(): GameUiState {
        repository.next()
        return GameUiState.InitialState(repository.getUnscrambleWord())
    }

    fun clickCheck(text: String): GameUiState {
        return if (repository.getOriginalWord() == text) {
            GameUiState.Correct(repository.getUnscrambleWord())
        } else GameUiState.Incorrect(repository.getUnscrambleWord())
    }

    fun clickSkip(): GameUiState {
        repository.next()
        return GameUiState.InitialState(repository.getUnscrambleWord())
    }

    fun checkSufficient(text: String): GameUiState {
        return if (repository.getOriginalWord().length == text.length) {
            GameUiState.Sufficient(repository.getUnscrambleWord())
        } else GameUiState.Insufficient(repository.getUnscrambleWord())
    }

    fun init(): GameUiState {
        return GameUiState.InitialState(repository.getUnscrambleWord())
    }

}
