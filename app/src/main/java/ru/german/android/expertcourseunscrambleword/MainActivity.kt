package ru.german.android.expertcourseunscrambleword

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import ru.german.android.expertcourseunscrambleword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = GameViewModel( object : GameRepository{
            override fun getUnscrambleWord(): String {
                TODO("Not yet implemented")
            }

            override fun getOriginalWord(): String {
                TODO("Not yet implemented")
            }

            override fun next() {
                TODO("Not yet implemented")
            }
        })

        binding.nextButton.setOnClickListener {
            val gameUiState: GameUiState = viewModel.clickNext()
            gameUiState.update(binding = binding)
        }

        binding.checkButton.setOnClickListener {
            val gameUiState: GameUiState =
                viewModel.clickCheck(text = binding.inputTextField.text.toString())
            gameUiState.update(binding = binding)
        }

        binding.skipButton.setOnClickListener {
            val gameUiState: GameUiState = viewModel.clickSkip()
            gameUiState.update(binding = binding)
        }

        binding.inputTextField.addTextChangedListener { editable ->

            val gameUiState: GameUiState = viewModel.checkSufficient(text = editable.toString())
            gameUiState.update(binding = binding)
        }

        val gameUiState: GameUiState = viewModel.init()
        gameUiState.update(binding = binding)

    }
}