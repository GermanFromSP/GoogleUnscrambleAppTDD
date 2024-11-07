package ru.german.android.expertcourseunscrambleword.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.german.android.expertcourseunscrambleword.R

class ScrambleWordUi(
    private val text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(R.id.scrambledWordTextView),
            isAssignableFrom(TextView::class.java)
        )
    )

    fun assertTextVisible() {
        interaction.check(matches(isCompletelyDisplayed()))
            .check(matches(withText(text)))
    }

    fun assertDoesNotExist() {
        interaction.check(doesNotExist())
    }

}
