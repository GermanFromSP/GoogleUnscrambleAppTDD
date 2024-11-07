package ru.german.android.expertcourseunscrambleword.load.presentation

import ru.german.android.expertcourseunscrambleword.core.MyViewModel
import ru.german.android.expertcourseunscrambleword.core.RunAsync
import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.load.data.LoadRepository

class LoadViewModel(
    private val repository: LoadRepository,
    observable: LoadUiObservable,
    private val runAsync: RunAsync,
    private val clearViewModel: ClearViewModel
) : MyViewModel.Abstract<LoadUiState>(observable) {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {

            observable.postUiState(LoadUiState.Progress)
            runAsync.handleAsync(viewModelScope, {

                val result = repository.load()
                if (result.isSuccessful()) {
                    clearViewModel.clear(LoadViewModel::class.java)
                    LoadUiState.Success
                }
                else
                    LoadUiState.Error(result.message())
            }) {
                observable.postUiState(it)
            }
        }
    }
}
