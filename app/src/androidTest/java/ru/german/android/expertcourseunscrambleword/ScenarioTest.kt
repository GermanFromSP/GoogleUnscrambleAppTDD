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

    private fun ActivityScenarioRule<*>.doWithRecreate(block: () -> Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }

    /**
     * Test case number 1 (UGTC - 01)
     */

    @Test
    fun caseNumber1() {
        // Context of the app under test.
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(userAnswer = "b")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "luetooth")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectAnswerState)

        gamePage.clickNext()
        gamePage = GamePage(word = "processor".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)
    }

    /**
     * Test case number 2 (UGTC - 02)
     */

    @Test
    fun caseNumber2() {
        // Context of the app under test.
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "processor".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(userAnswer = "rop")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "drone".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(userAnswer = "ro")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "den")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "light".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(userAnswer = "gih")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "tl")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertIncorrectAnswerState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "tripple".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(userAnswer = "rip")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "lept")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertIncorrectAnswerState)

        gamePage.removeInput()
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "p")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.removeInput()
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "t")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertIncorrectAnswerState)
    }
}