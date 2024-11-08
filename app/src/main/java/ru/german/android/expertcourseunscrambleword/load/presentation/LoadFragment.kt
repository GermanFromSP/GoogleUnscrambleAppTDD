package ru.german.android.expertcourseunscrambleword.load.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.german.android.expertcourseunscrambleword.core.AbstractFragment
import ru.german.android.expertcourseunscrambleword.databinding.FragmentLoadBinding
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.game.NavigateToGame

class LoadFragment : AbstractFragment<LoadUiState, LoadViewModel>() {

    private var _binding: FragmentLoadBinding? = null

    override val update: (LoadUiState) -> Unit = { uiState ->
        uiState.show(
            binding.errorTextView,
            binding.loadProgress,
            binding.retryButton
        )

        uiState.navigate((requireActivity() as NavigateToGame))
    }

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).makeViewModel(LoadViewModel::class.java)

        binding.retryButton.setOnClickListener {
            viewModel.load(true)
        }

        viewModel.load(isFirstRun = savedInstanceState == null)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}