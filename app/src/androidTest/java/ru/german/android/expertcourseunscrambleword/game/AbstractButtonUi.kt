package ru.german.android.expertcourseunscrambleword.game

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.CoreMatchers.not


abstract class AbstractButton(
    protected val interaction: ViewInteraction
) {

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertInvisible() {
        interaction.check(matches(not(isDisplayed())))
    }


}