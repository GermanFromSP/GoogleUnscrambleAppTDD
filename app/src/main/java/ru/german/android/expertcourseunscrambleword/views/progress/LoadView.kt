package ru.german.android.expertcourseunscrambleword.views.progress

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.ProgressBar
import ru.german.android.expertcourseunscrambleword.views.visibilitybutton.UpdateVisibility
import ru.german.android.expertcourseunscrambleword.views.visibilitybutton.VisibilitySavedState
import ru.german.android.expertcourseunscrambleword.views.visibilitybutton.VisibilityState

class LoadView : ProgressBar, UpdateVisibility {

    private lateinit var state: VisibilityState

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as VisibilitySavedState
        super.onRestoreInstanceState(restoreState.superState)
        update(restoreState.restore())
    }

    override fun update(visibility: Int) {
       this.visibility = visibility
    }

    override fun update(state: VisibilityState) {
        this.state = state
        state.update(this)
    }

}