package ru.german.android.expertcourseunscrambleword.views.input

import android.content.Context
import android.os.Parcelable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.german.android.expertcourseunscrambleword.InputUiState
import ru.german.android.expertcourseunscrambleword.R
import ru.german.android.expertcourseunscrambleword.databinding.InputBinding

class InputView : FrameLayout, UpdateInput {

    private val binding = InputBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var state: InputUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = InputViewSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as InputViewSavedState
        super.onRestoreInstanceState(restoreState.superState)
        update(restoreState.restore())
    }

    override fun update(uiState: InputUiState) {
        state = uiState
        state.update(this)
    }

    override fun update(userInput: String) {
        binding.inputTextField.setText(userInput)
    }

    override fun update(errorIsEnabled: Boolean, enabled: Boolean) {

        with(binding) {
            inputTextLayout.isErrorEnabled = errorIsEnabled
            if (errorIsEnabled) inputTextLayout.error =
                inputTextLayout.context.getString(R.string.error_mesage)
            inputTextLayout.isEnabled = enabled
        }
    }

    fun addTextChangedListener(textWatcher: TextWatcher) {
        binding.inputTextField.addTextChangedListener(textWatcher)
    }

    fun removeTextChangedListener(textWatcher: TextWatcher) {
       binding.inputTextField.removeTextChangedListener(textWatcher)
    }

    fun text(): String {
        return binding.inputTextField.text.toString()
    }

}

interface UpdateInput {
    fun update(uiState: InputUiState)

    fun update(userInput: String)
    fun update(errorIsEnabled: Boolean, enabled: Boolean)

}
