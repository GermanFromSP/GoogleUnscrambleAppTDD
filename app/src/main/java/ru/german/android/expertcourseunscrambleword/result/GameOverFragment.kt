package ru.german.android.expertcourseunscrambleword.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.databinding.GameOverBinding
import ru.german.android.expertcourseunscrambleword.game.NavigateToGame

class GameOverFragment : Fragment() {

    private var _binding: GameOverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameOverBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (requireActivity() as ProvideViewModel).makeViewModel(GameOverViewModel::class.java)

        binding.statsTextView.update(viewModel.getStatsUiState())

        binding.newGameButton.setOnClickListener {
            viewModel.clear()
            (requireActivity() as NavigateToGame).navigateToGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}