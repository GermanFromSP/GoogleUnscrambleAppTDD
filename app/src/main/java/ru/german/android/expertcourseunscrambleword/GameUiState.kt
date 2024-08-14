package ru.german.android.expertcourseunscrambleword

import ru.german.android.expertcourseunscrambleword.databinding.ActivityMainBinding

interface GameUiState {


    fun update(binding: ActivityMainBinding)

    data class InitialState(private val shuffledWord: String) : GameUiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

    }

   data class Insufficient(private val shuffledWord: String) : GameUiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }
    }

   data class Sufficient(private val shuffledWord: String) : GameUiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

    }

   data class Correct(private val shuffledWord: String) : GameUiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

    }

   data class Incorrect(private val shuffledWord: String) : GameUiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

    }


}