package ru.german.android.expertcourseunscrambleword

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.game.GameScreen
import ru.german.android.expertcourseunscrambleword.game.NavigateToGame
import ru.german.android.expertcourseunscrambleword.result.GameOverScreen
import ru.german.android.expertcourseunscrambleword.result.NavigateToGameOver
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), Navigate, ProvideViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) navigateToGame()
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }

    override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
      return (application as UnscrambleApp).makeViewModel(clazz)
    }
}

interface Navigate : NavigateToGame, NavigateToGameOver {

    fun navigate(screen: Screen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToGameOver() = navigate(GameOverScreen)
}
