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


    /**
     * Test case number 3 (UGTC - 03)
     */

    @Test
    fun caseNumber3() {
        //region 5 correct answers
        //first word
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

        //second word
        gamePage.addInput(userAnswer = "p")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "rocessor")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectAnswerState)

        gamePage.clickNext()
        gamePage = GamePage(word = "drone".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //third word
        gamePage.addInput(userAnswer = "d")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "rone")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectAnswerState)

        gamePage.clickNext()
        gamePage = GamePage(word = "light".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //fourth word
        gamePage.addInput(userAnswer = "l")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "ight")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectAnswerState)

        gamePage.clickNext()
        gamePage = GamePage(word = "tripple".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //fifth word
        gamePage.addInput(userAnswer = "t")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "ripple")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectAnswerState)

        gamePage.clickNext()
        gamePage.assertNotVisible()

        var gameOverPage = GameOverPage(correct = 5, incorrect = 0)
        activityScenarioRule.doWithRecreate(gameOverPage::assertInitialState)

        gameOverPage.clickNewGame()
        gameOverPage.assertNotVisible()
        //endregion

        //region 5 incorrect answers
        //first word

        gamePage = GamePage(word = "bluetooth".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "processor".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //second word
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "drone".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //third word
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "light".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //fourth word
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "tripple".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //fifth word
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage.assertNotVisible()

        gameOverPage = GameOverPage(correct = 0, incorrect = 5)
        activityScenarioRule.doWithRecreate(gameOverPage::assertInitialState)

        gameOverPage.clickNewGame()
        gameOverPage.assertNotVisible()
        //endregion

        //region 2 correct 3 incorrect answers
        //first word
        gamePage = GamePage(word = "bluetooth".reversed())
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

        //second word
        gamePage.addInput(userAnswer = "p")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientInputState)

        gamePage.addInput(userAnswer = "rocessor")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientInputState)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectAnswerState)

        gamePage.clickNext()
        gamePage = GamePage(word = "drone".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //third word
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "light".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //fourth word
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "tripple".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        //fifth word
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage.assertNotVisible()

        gameOverPage = GameOverPage(correct = 2, incorrect = 3)
        activityScenarioRule.doWithRecreate(gameOverPage::assertInitialState)

        gameOverPage.clickNewGame()
        gameOverPage.assertNotVisible()
        //endregion
    }

    /**
     * Test case number 4 (UGTC - 04)
     */

    @Test
    fun caseNumber4() {
        val loadPage = LoadPage()

        activityScenarioRule.doWithRecreate(loadPage::assertErrorState)

        loadPage.clickRetry()

        activityScenarioRule.doWithRecreate(loadPage::assertProgressState)

        loadPage.waitTillGone()

        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)
    }
}