package ru.german.android.expertcourseunscrambleword.views.stats

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ru.german.android.expertcourseunscrambleword.R
import java.io.Serializable

class StatsTextView : AppCompatTextView, UpdateStats {

    private lateinit var state: StatsUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = StatsSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as StatsSavedState
        super.onRestoreInstanceState(restoreState.superState)
        update(restoreState.restore())
    }

    override fun update(uiState: StatsUiState) {
        state = uiState
        state.update(this)
    }

    override fun update(corrects: Int, incorrect: Int) {
        text = resources.getString(R.string.stats, corrects, incorrect)

    }

}

interface StatsUiState : Serializable {

    fun update(statsTextView: UpdateStats)

    class Base(private val corrects: Int, private val incorrect: Int) : StatsUiState {
        override fun update(statsTextView: UpdateStats) {
            statsTextView.update(corrects, incorrect)
        }

    }
}

interface UpdateStats {
    fun update(uiState: StatsUiState)
    fun update(corrects: Int, incorrect: Int)
}