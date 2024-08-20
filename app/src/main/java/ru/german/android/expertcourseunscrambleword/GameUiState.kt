package ru.german.android.expertcourseunscrambleword

import android.view.View
import android.widget.Button
import androidx.transition.Visibility
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.german.android.expertcourseunscrambleword.databinding.ActivityMainBinding
import java.io.Serializable

interface GameUiState : Serializable {


    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val shuffledWord: String,
        private val textInputUiState: InputUiState,
        private val skipVisibility: Int,
        private val checkUiState: CheckUiState,
        private val nextVisibility: Int = View.GONE
    ) : GameUiState {

        override fun update(binding: ActivityMainBinding) = with(binding) {
            scrambledWordTextView.text = shuffledWord
            skipButton.visibility = skipVisibility
            nextButton.visibility = nextVisibility
            checkUiState.update(checkButton)
            textInputUiState.update(inputTextLayout, inputTextField)
        }
    }

    data class InitialState(private val shuffledWord: String) : Abstract(
            shuffledWord = shuffledWord,
            textInputUiState = InputUiState.Initial,
            skipVisibility = View.VISIBLE,
            checkUiState = CheckUiState.Disabled
        )

    data class Insufficient(private val shuffledWord: String) :  Abstract(
        shuffledWord = shuffledWord,
        textInputUiState = InputUiState.EnabledNoError,
        skipVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Disabled
    )

    data class Sufficient(private val shuffledWord: String) :  Abstract(
        shuffledWord = shuffledWord,
        textInputUiState = InputUiState.EnabledNoError,
        skipVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Enabled
    )

    data class Correct(private val shuffledWord: String) :  Abstract(
        shuffledWord = shuffledWord,
        textInputUiState = InputUiState.Correct,
        skipVisibility = View.GONE,
        checkUiState = CheckUiState.Invisible,
        nextVisibility = View.VISIBLE
    )

    data class Incorrect(private val shuffledWord: String) :  Abstract(
        shuffledWord = shuffledWord,
        textInputUiState = InputUiState.Incorrect,
        skipVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Disabled
    )
}

interface CheckUiState : Serializable {

    fun update(button: Button)

    abstract class Abstract(
        private val visibility: Int,
        private val enabled: Boolean
    ) : CheckUiState {
        override fun update(button: Button) {
            button.visibility = visibility
            button.isEnabled = enabled
        }
    }

    object Disabled : Abstract(visibility = View.VISIBLE, enabled = false)
    object Enabled : Abstract(visibility = View.VISIBLE, enabled = true)
    object Invisible : Abstract(visibility = View.GONE, enabled = false)
}

interface InputUiState : Serializable {

    fun update(textInputLayout: TextInputLayout, textInputField: TextInputEditText)

    abstract class Abstract(
        private val errorIsVisible: Boolean,
        private val enabled: Boolean,
        private val clearText: Boolean
    ) : InputUiState {

        override fun update(textInputLayout: TextInputLayout, textInputField: TextInputEditText) {
            textInputLayout.isErrorEnabled = errorIsVisible
            if (errorIsVisible) textInputLayout.error =
                textInputLayout.context.getString(R.string.error_mesage)
            textInputLayout.isEnabled = enabled
            if (clearText) textInputField.setText(R.string.empty)
        }
    }

    object Initial : Abstract(false, true, true)

    object EnabledNoError : Abstract(false, true, false)

    object Correct : Abstract(false, false, false)

    object Incorrect : Abstract(true, true, false)

}
