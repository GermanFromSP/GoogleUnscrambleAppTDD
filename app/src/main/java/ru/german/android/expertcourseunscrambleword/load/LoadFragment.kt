package ru.german.android.expertcourseunscrambleword.load

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.german.android.expertcourseunscrambleword.databinding.FragmentLoadBinding
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.game.GameViewModel

class LoadFragment : Fragment() {

    private var _binding: FragmentLoadBinding? = null
    private lateinit var viewModel: LoadViewModel

    private val update: (LoadUiState) -> Unit = { uiState ->
        uiState.show(
            binding.errorTextView,
            binding.loadProgress,
            binding.retryButton
        )

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

        viewModel.load(firstRun = savedInstanceState == null)

    }

    override fun onResume() {
        super.onResume()
        viewModel.startUpdates(observer = update)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}