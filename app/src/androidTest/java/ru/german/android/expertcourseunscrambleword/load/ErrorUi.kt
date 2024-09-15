package ru.german.android.expertcourseunscrambleword.load

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import ru.german.android.expertcourseunscrambleword.R

class ErrorUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) {

    private val viewId = R.id.errorTextView
    private val interaction: ViewInteraction = onView(
        allOf(
            withId(viewId),
            withText(R.string.no_internet),
            isAssignableFrom(TextView::class.java),
            containerIdMatcher,
            containerClassTypeMatcher
        )
    )

    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun waitTillVisible() {
        onView(isRoot()).perform(waitTillDisplayed(viewId, 4000))
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun waitTillDoesNotExist() {
        onView(isRoot()).perform(waitTillDoesNotExist(viewId, 4000))
    }

}
