package ru.german.android.expertcourseunscrambleword

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.german.android.expertcourseunscrambleword.game.GameScreen
import ru.german.android.expertcourseunscrambleword.game.NavigateToGame
import ru.german.android.expertcourseunscrambleword.result.GameOverScreen
import ru.german.android.expertcourseunscrambleword.result.NavigateToGameOver

class MainActivity : AppCompatActivity(), Navigate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) navigateToGame()
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }
}

interface Navigate : NavigateToGame, NavigateToGameOver {

    fun navigate(screen: Screen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToGameOver() = navigate(GameOverScreen)
}
