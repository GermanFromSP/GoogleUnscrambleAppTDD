package ru.german.android.expertcourseunscrambleword.game

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.german.android.expertcourseunscrambleword.core.AbstractFragment
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.databinding.GameFragmentBinding
import ru.german.android.expertcourseunscrambleword.result.NavigateToGameOver

class GameFragment : AbstractFragment<GameUiState, GameViewModel>() {

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            viewModel.checkSufficient(text = s.toString())
        }
    }

    override val update: (GameUiState) -> Unit = { uiState ->
        uiState.update(
            binding.scrambledWordTextView,
            binding.inputView,
            binding.skipButton,
            binding.checkButton,
            binding.nextButton
        )
        uiState.navigate(requireActivity() as NavigateToGameOver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (requireActivity() as ProvideViewModel).makeViewModel(GameViewModel::class.java)

        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            viewModel.clickNext()
        }

        binding.checkButton.setOnClickListener {
            viewModel.clickCheck(text = binding.inputView.text())

        }

        binding.skipButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.clickSkip()
            }


        }

        viewModel.init(savedInstanceState == null)

    }

    override fun onResume() {
        super.onResume()
        binding.inputView.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputView.removeTextChangedListener(textWatcher)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}