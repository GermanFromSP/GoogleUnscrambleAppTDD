package ru.german.android.expertcourseunscrambleword.core

import androidx.fragment.app.Fragment
import ru.german.android.expertcourseunscrambleword.load.presentation.LoadUiState

abstract class AbstractFragment<UiState : Any, T: MyViewModel<UiState>> : Fragment() {

    protected lateinit var viewModel: T

    protected abstract val update: (UiState) -> Unit

    override fun onResume() {
        super.onResume()
        viewModel.startUpdates(observer = update)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdates()
    }
}