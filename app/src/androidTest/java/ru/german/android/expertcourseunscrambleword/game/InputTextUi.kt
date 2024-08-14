package ru.german.android.expertcourseunscrambleword.game

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.german.android.expertcourseunscrambleword.R

class InputTextUi(
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {

    private fun getInputLayoutErrorState(boolean: Boolean): TextInputLayoutErrorEnabledMatcher {
        return TextInputLayoutErrorEnabledMatcher(boolean)
    }


    private val inputLayoutInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputLayout::class.java),
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(R.id.inputTextLayout),

        )
    )

    private val inputTextInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
//            containerIdMatcher,
//            containerClassTypeMatcher,
            withId(R.id.inputTextField),
//            withParent(withId(R.id.inputTextLayout)),
//            withParent(isAssignableFrom(TextInputLayout::class.java)),

        )
    )

    fun assertInitialState() {
        inputLayoutInteraction
            .check(matches(isEnabled()))
            .check(matches(getInputLayoutErrorState(false)))
        inputTextInteraction.check(matches(withText("")))
    }

    fun addInput(text: String) {
        inputTextInteraction.perform(typeText(text), closeSoftKeyboard())
    }

    fun assertSufficientState() {
        inputLayoutInteraction
            .check(matches(isEnabled()))
            .check(matches(getInputLayoutErrorState(false)))
    }

    fun assertCorrectState() {
        inputLayoutInteraction
            .check(matches(isNotEnabled()))
            .check(matches(getInputLayoutErrorState(false)))
    }

    fun assertIncorrectState() {
        inputLayoutInteraction
            .check(matches(isEnabled()))
            .check(matches(getInputLayoutErrorState(true)))
    }

    fun removeInput() {
        inputTextInteraction.perform(
            click(),
            pressKey(KeyEvent.KEYCODE_DEL),
            closeSoftKeyboard()
        )
    }

    fun assertInsufficientState() {
        inputLayoutInteraction
            .check(matches(isEnabled()))
            .check(matches(getInputLayoutErrorState(false)))
    }

}
