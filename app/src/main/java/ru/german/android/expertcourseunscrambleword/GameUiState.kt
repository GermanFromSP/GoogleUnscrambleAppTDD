package ru.german.android.expertcourseunscrambleword

import android.view.View
import ru.german.android.expertcourseunscrambleword.databinding.ActivityMainBinding
import ru.german.android.expertcourseunscrambleword.result.NavigateToGameOver
import ru.german.android.expertcourseunscrambleword.views.check.UpdateCheckButton
import ru.german.android.expertcourseunscrambleword.views.input.UpdateInput
import ru.german.android.expertcourseunscrambleword.views.scrambleword.UpdateText
import ru.german.android.expertcourseunscrambleword.views.visibilitybutton.UpdateVisibility
import ru.german.android.expertcourseunscrambleword.views.visibilitybutton.VisibilityState
import java.io.Serializable

interface GameUiState {


    fun update(
        questionTextView: UpdateText,
        inputView: UpdateInput,
        skip: UpdateVisibility,
        check: UpdateCheckButton,
        next: UpdateVisibility
    ) = Unit

    fun navigate(navigate: NavigateToGameOver) = Unit

    object Finish : GameUiState {

        override fun navigate(navigate: NavigateToGameOver) = navigate.navigateToGameOver()
    }

    object Empty : GameUiState {

        override fun update(
            questionTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) = Unit


    }

    abstract class Abstract(

        private val textInputUiState: InputUiState,
        private val checkUiState: CheckUiState,
    ) : GameUiState {

        override fun update(
            questionTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            inputView.update(textInputUiState)
            skip.update(VisibilityState.Visible)
            check.update(checkUiState)
        }

    }

    data class InitialState(
        private val shuffledWord: String,
        private val userInput: String = ""
    ) : Abstract(
        textInputUiState = InputUiState.Initial(userInput),
        checkUiState = CheckUiState.Disabled,
    ) {
        override fun update(
            questionTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            super.update(questionTextView, inputView, skip, check, next)
            questionTextView.update(shuffledWord)
            next.update(VisibilityState.Gone)
        }
    }

    object Insufficient : Abstract(
        textInputUiState = InputUiState.EnabledNoError,
        checkUiState = CheckUiState.Disabled,
    )

    object Sufficient : Abstract(
        textInputUiState = InputUiState.EnabledNoError,
        checkUiState = CheckUiState.Enabled,
    )

    object Correct : Abstract(
        textInputUiState = InputUiState.Correct,
        checkUiState = CheckUiState.Invisible,
    ) {
        override fun update(
            questionTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            super.update(questionTextView, inputView, skip, check, next)
            next.update(VisibilityState.Visible)
            skip.update(VisibilityState.Gone)
        }
    }

    object Incorrect : Abstract(
        textInputUiState = InputUiState.Incorrect,
        checkUiState = CheckUiState.Disabled,
    )
}

interface CheckUiState : Serializable {

    fun update(updateCheckButton: UpdateCheckButton)

    abstract class Abstract(
        private val visibility: Int,
        private val enabled: Boolean
    ) : CheckUiState {

        override fun update(updateCheckButton: UpdateCheckButton) {
            updateCheckButton.update(visibility, enabled)
        }
    }

    object Disabled : Abstract(visibility = View.VISIBLE, enabled = false)
    object Enabled : Abstract(visibility = View.VISIBLE, enabled = true)
    object Invisible : Abstract(visibility = View.GONE, enabled = false)
}

interface InputUiState : Serializable {

    fun update(updateInput: UpdateInput)

    abstract class Abstract(
        private val errorIsVisible: Boolean,
        private val enabled: Boolean,
    ) : InputUiState {

        override fun update(updateInput: UpdateInput) {
            updateInput.update(errorIsVisible, enabled)
        }
    }

    data class Initial(private val userInput: String) : Abstract(false, true) {

        override fun update(updateInput: UpdateInput) {
            super.update(updateInput)
            updateInput.update(userInput)
        }
    }

    object EnabledNoError : Abstract(false, true)

    object Correct : Abstract(false, false)

    object Incorrect : Abstract(true, true)

}
