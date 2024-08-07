package ru.german.android.expertcourseunscrambleword.game

import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.german.android.expertcourseunscrambleword.R

class CheckButtonUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) :
    AbstractButton(
        onView(
            allOf(
                withId(R.id.checkButton),
                ButtonColorMatcher("#558F85"),
                withText(R.string.check),
                containerIdMatcher,
                containerClassTypeMatcher,
                isAssignableFrom(AppCompatButton::class.java)
            )
        )
    ) {

    fun assertVisibleDisabled() {
       interaction.check(matches(isNotEnabled()))
           .check(matches(isDisplayed()))
    }

    fun assertEnabled() {
        interaction.check(matches(isEnabled()))
            .check(matches(isDisplayed()))
    }

}
