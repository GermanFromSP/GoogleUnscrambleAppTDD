package ru.german.android.expertcourseunscrambleword.game

import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.core.MyViewModel
import ru.german.android.expertcourseunscrambleword.core.RunAsync

class GameViewModel(
    uiObservable: GameUiObservable,
    private val clearViewModel: ClearViewModel,
    private val repository: GameRepository,
    private val runAsync: RunAsync
) : MyViewModel.Abstract<GameUiState>(uiObservable) {

    private val uiUpdate: (GameUiState) -> Unit = {
        uiObservable.postUiState(it)
    }

    fun clickNext() {
        repository.next()
        return if (repository.isLastQuestion()) {
            runAsync.handleAsync(viewModelScope, {
                repository.clear()
                clearViewModel.clear(GameViewModel::class.java)
                GameUiState.Finish
            }, uiUpdate)
        } else
            init()

    }

    fun clickCheck(text: String) {
        runAsync.handleAsync(viewModelScope, {
            if (repository.getOriginalWord() == text) {
                GameUiState.Correct
            } else GameUiState.Incorrect
        }, uiUpdate)
    }

    fun clickSkip() {
        repository.skip()
        return if (repository.isLastQuestion()) {
            runAsync.handleAsync(viewModelScope, {
                repository.clear()
                GameUiState.Finish
            }, uiUpdate)
        } else
            init()
    }

    fun checkSufficient(text: String) {
        runAsync.handleAsync(viewModelScope, {
            if (repository.getOriginalWord().length == text.length) {
                GameUiState.Sufficient
            } else GameUiState.Insufficient
        }, uiUpdate)

    }

    fun init(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            runAsync.handleAsync(viewModelScope, {
                GameUiState.InitialState(repository.getUnscrambleWord())
            }, uiUpdate)
        }
    }
}
