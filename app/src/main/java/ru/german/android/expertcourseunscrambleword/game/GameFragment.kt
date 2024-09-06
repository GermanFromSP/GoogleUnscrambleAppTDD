package ru.german.android.expertcourseunscrambleword.game

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.german.android.expertcourseunscrambleword.UnscrambleApp
import ru.german.android.expertcourseunscrambleword.databinding.GameFragmentBinding
import ru.german.android.expertcourseunscrambleword.result.NavigateToGameOver

class GameFragment : Fragment() {


    private lateinit var gameUiState: GameUiState
    private lateinit var viewModel: GameViewModel

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            gameUiState = viewModel.checkSufficient(text = s.toString())
            update.invoke()
        }
    }

    private val update: () -> Unit = {
        gameUiState.update(
            binding.scrambledWordTextView,
            binding.inputView,
            binding.skipButton,
            binding.checkButton,
            binding.nextButton
        )
        gameUiState.navigate(requireActivity() as NavigateToGameOver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (requireActivity().application as UnscrambleApp).viewModel
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            gameUiState = viewModel.clickNext()
            update.invoke()
        }

        binding.checkButton.setOnClickListener {
            gameUiState = viewModel.clickCheck(text = binding.inputView.text())
            update.invoke()
        }

        binding.skipButton.setOnClickListener {
            gameUiState = viewModel.clickSkip()
            update.invoke()
        }

        gameUiState = viewModel.init(savedInstanceState == null)
        update.invoke()
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