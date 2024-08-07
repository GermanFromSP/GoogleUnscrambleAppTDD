package ru.german.android.expertcourseunscrambleword

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
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

    private lateinit var gamePage: GamePage

    @Before
    fun setupNewWord(word: String = "bluetooth") {
        gamePage = GamePage(word = word.reversed())
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
        setupNewWord(word = "processor")
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
        setupNewWord(word = "processor")
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "rop")
        gamePage.assertSufficientInputState()

        gamePage.clickSkip()
        setupNewWord("drone")
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "ro")
        gamePage.assertSufficientInputState()

        gamePage.addInput(userAnswer = "den")
        gamePage.assertSufficientInputState()

        gamePage.clickSkip()
        setupNewWord("light")
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "gih")
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "tl")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()

        gamePage.clickSkip()
        setupNewWord("tripple")
        gamePage.assertInitialState()

        gamePage.addInput(userAnswer = "rip")
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "lepet")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()

        gamePage.removeInput()
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "ripletep")
        gamePage.assertSufficientInputState()

        gamePage.removeInput()
        gamePage.assertInsufficientInputState()

        gamePage.addInput(userAnswer = "pleritep")
        gamePage.assertSufficientInputState()

        gamePage.clickCheck()
        gamePage.assertIncorrectAnswerState()
    }
}