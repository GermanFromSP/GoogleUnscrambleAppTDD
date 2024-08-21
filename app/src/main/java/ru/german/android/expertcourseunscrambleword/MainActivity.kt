package ru.german.android.expertcourseunscrambleword

import android.os.Build
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
            gameUiState.update(binding = binding)
        }
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
            gameUiState.update(binding = binding)
        }

        binding.checkButton.setOnClickListener {
            gameUiState = viewModel.clickCheck(text = binding.inputTextField.text.toString())
            gameUiState.update(binding = binding)
        }

        binding.skipButton.setOnClickListener {
            gameUiState = viewModel.clickSkip()
            gameUiState.update(binding = binding)
        }

        gameUiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(
                    "uiState",
                    GameUiState::class.java
                ) as GameUiState
            } else {
                savedInstanceState.getSerializable("uiState") as GameUiState
            }
        }
        gameUiState.update(binding = binding)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable("uiState", gameUiState)
    }

    override fun onResume() {
        super.onResume()
        binding.inputTextField.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputTextField.removeTextChangedListener(textWatcher)
    }
}