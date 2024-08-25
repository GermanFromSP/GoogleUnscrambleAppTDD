package ru.german.android.expertcourseunscrambleword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.german.android.expertcourseunscrambleword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var gameUiState: GameUiState
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GameViewModel

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (application as UnscrambleApp).viewModel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
}