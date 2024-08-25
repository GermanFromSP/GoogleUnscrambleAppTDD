package ru.german.android.expertcourseunscrambleword.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.german.android.expertcourseunscrambleword.R

class GamePage(word: String) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val scrambledWordUi = ScrambleWordUi(
        text = word,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val inputTextUi = InputTextUi()

    private val skipUi = ButtonUi(
        id = R.id.skipButton,
        buttonTextRes = R.string.skip,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val checkUi = CheckButtonUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        buttonTextRes = R.string.next,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    fun assertInitialState() {
        scrambledWordUi.assertTextVisible()
        checkUi.assertVisibleDisabled()
        inputTextUi.assertInitialState()
        skipUi.assertVisible()
        nextUi.assertInvisible()
    }

    fun addInput(userAnswer: String) {
        inputTextUi.addInput(text = userAnswer)
    }

    fun assertInsufficientInputState() {
        scrambledWordUi.assertTextVisible()
        checkUi.assertVisibleDisabled()
        inputTextUi.assertInsufficientState()
        skipUi.assertVisible()
        nextUi.assertInvisible()
    }

    fun assertSufficientInputState() {
        scrambledWordUi.assertTextVisible()
        inputTextUi.assertSufficientState()
        checkUi.assertEnabled()
        skipUi.assertVisible()
        nextUi.assertInvisible()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertCorrectAnswerState() {
        scrambledWordUi.assertTextVisible()
        checkUi.assertInvisible()
        skipUi.assertInvisible()
        nextUi.assertVisible()
        inputTextUi.assertCorrectState()
    }

    fun clickNext() {
        nextUi.click()
    }

    fun clickSkip() {
        skipUi.click()
    }

    fun assertIncorrectAnswerState() {
        scrambledWordUi.assertTextVisible()
        checkUi.assertVisibleDisabled()
        skipUi.assertVisible()
        nextUi.assertInvisible()
        inputTextUi.assertIncorrectState()
    }

    fun removeInput() {
        inputTextUi.removeInput()
    }
}