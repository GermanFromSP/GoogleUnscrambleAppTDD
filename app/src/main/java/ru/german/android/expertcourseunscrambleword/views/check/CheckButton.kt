package ru.german.android.expertcourseunscrambleword.views.check

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import ru.german.android.expertcourseunscrambleword.CheckUiState

class CheckButton : AppCompatButton, UpdateCheckButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private lateinit var state: CheckUiState

    override fun update(state: CheckUiState) {
        this.state = state
        state.update(this)
    }

    override fun update(visibility: Int, enabled: Boolean) {
        this.visibility = visibility
        this.isEnabled = enabled
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = CheckButtonSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as CheckButtonSavedState
        super.onRestoreInstanceState(restoreState.superState)
        update(restoreState.restore())
    }

}

interface UpdateCheckButton  {

    fun update(state: CheckUiState)

    fun update(visibility: Int, enabled: Boolean)
}