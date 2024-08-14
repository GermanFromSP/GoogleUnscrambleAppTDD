package ru.german.android.expertcourseunscrambleword

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.german.android.expertcourseunscrambleword.game.GamePage

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setupNewWord() {
        gamePage = GamePage(word = "bluetooth".reversed())
    }

    /**
     * Test case number 1 (UGTC - 01)
     */

    @Test
    fun caseNumber1() {
        // Context of the app under test.
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "b")
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "luetooth")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertCorrectAnswerState()

        gamePage.clickNext()
        gamePage = GamePage(word = "processor".reversed())
        gamePage.assertInitialState()
    }

    /**
     * Test case number 2 (UGTC - 02)
     */

    @Test
    fun caseNumber2() {
        // Context of the app under test.
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "processor".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "rop")
        gamePage.assertInsufficientInputState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "drone".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "ro")
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "den")
        gamePage.assertSufficientInputState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "light".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "gih")
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "tl")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "tripple".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "rip")
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "lept")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()

        gamePage.removeInput()
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "p")
        gamePage.assertSufficientInputState()

        gamePage.removeInput()
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "t")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()
    }
}