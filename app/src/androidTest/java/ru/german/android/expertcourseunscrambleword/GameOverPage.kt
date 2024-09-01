package ru.german.android.expertcourseunscrambleword

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.german.android.expertcourseunscrambleword.game.ButtonUi

class GameOverPage(correct: Int, incorrect: Int) {


    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.gameOverContainer))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(FrameLayout::class.java))

    private val statsUi = StatsUi(
        incorrect = incorrect,
        correct = correct,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val newGameUi = ButtonUi(
        id = R.id.newGameButton,
        buttonTextRes = R.string.newGame,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    fun assertInitialState() {
        statsUi.assertVisible()
    }

    fun clickNewGame() {
       newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.assertDoesNotExist()
    }

}
