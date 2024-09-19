package ru.german.android.expertcourseunscrambleword.load

import ru.german.android.expertcourseunscrambleword.R
import ru.german.android.expertcourseunscrambleword.game.NavigateToGame
import ru.german.android.expertcourseunscrambleword.views.error.ErrorUiState
import ru.german.android.expertcourseunscrambleword.views.error.UpdateError
import ru.german.android.expertcourseunscrambleword.views.visibilitybutton.UpdateVisibility
import ru.german.android.expertcourseunscrambleword.views.visibilitybutton.VisibilityState

interface LoadUiState {

    fun show(
        errorTextView: UpdateError,
        loadProgress: UpdateVisibility,
        retryButton: UpdateVisibility
    )

    fun navigate(navigateToGame: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorUiState: ErrorUiState,
        private val retryUiState: VisibilityState,
        private val progressUiState: VisibilityState
    ) : LoadUiState {

        override fun show(
            errorTextView: UpdateError,
            loadProgress: UpdateVisibility,
            retryButton: UpdateVisibility
        ) {
            errorTextView.update(errorUiState)
            retryButton.update(retryUiState)
            loadProgress.update(progressUiState)
        }
    }

    data class Error(private val message: String) : Abstract(
        errorUiState = ErrorUiState.Show(R.string.error_mesage),
        retryUiState = VisibilityState.Visible,
        progressUiState = VisibilityState.Gone
    )

    object Progress : Abstract(
        errorUiState = ErrorUiState.Hide,
        retryUiState = VisibilityState.Gone,
        progressUiState = VisibilityState.Visible
    )

    object Success : Abstract(
        errorUiState = ErrorUiState.Hide,
        retryUiState = VisibilityState.Gone,
        progressUiState = VisibilityState.Gone
    ) {
        override fun navigate(navigateToGame: NavigateToGame) = navigateToGame.navigateToGame()
    }
}