package ru.german.android.expertcourseunscrambleword.views.visibilitybutton


import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import java.io.Serializable


class VisibilitySavedState : View.BaseSavedState {

    private lateinit var state: VisibilityState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                VisibilityState::class.java.classLoader,
                VisibilityState::class.java
            ) as VisibilityState
        } else {
            parcelIn.readSerializable() as VisibilityState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): VisibilityState = state

    fun save(uiState: VisibilityState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VisibilitySavedState> {
        override fun createFromParcel(parcel: Parcel): VisibilitySavedState =
            VisibilitySavedState(parcel)

        override fun newArray(size: Int): Array<VisibilitySavedState?> =
            arrayOfNulls(size)
    }

}

interface VisibilityState : Serializable {

    fun update(updateVisibility: UpdateVisibility)

    abstract class Abstract(private val visibility: Int) : VisibilityState {
        override fun update(updateVisibility: UpdateVisibility) =
            updateVisibility.update(visibility)
    }

    object Visible : Abstract(View.VISIBLE)
    object Gone : Abstract(View.GONE)

}