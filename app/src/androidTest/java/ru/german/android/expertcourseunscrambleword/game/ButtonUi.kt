package ru.german.android.expertcourseunscrambleword.game

import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ButtonUi(
    id: Int,
    buttonTextRes: Int,
    buttonColorHex: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButton(
        onView(
            allOf(
                withId(id),
                ButtonColorMatcher(buttonColorHex),
                withText(buttonTextRes),
                containerIdMatcher,
                containerClassTypeMatcher,
                isAssignableFrom(Button::class.java)
            )
        )
) {

}
