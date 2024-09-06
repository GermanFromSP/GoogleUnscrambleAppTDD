package ru.german.android.expertcourseunscrambleword

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class StatsUi(
    incorrect: Int,
    correct: Int,
    private val containerIdMatcher: Matcher<View>,
    private val containerClassTypeMatcher: Matcher<View>
) {

    private val interaction = onView(
        allOf(
            withId(R.id.statsTextView),
            isAssignableFrom(AppCompatTextView::class.java),
            withText("Corrects: $correct\nIncorrects: $incorrect") ,
            containerIdMatcher,
            containerClassTypeMatcher
        )

    )

    fun assertVisible() {
       interaction.check(matches(isDisplayed()))
    }

    fun assertDoesNotExist() {
        interaction.check(doesNotExist())
    }

}
