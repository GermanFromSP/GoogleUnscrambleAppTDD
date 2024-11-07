package ru.german.android.expertcourseunscrambleword.load

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.german.android.expertcourseunscrambleword.R
import ru.german.android.expertcourseunscrambleword.game.ButtonUi

class LoadPage {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.loadContainer))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val retryButtonUi = ButtonUi(
        id = R.id.retryButton ,
        buttonTextRes = R.string.retry,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val progressUi = ProgressUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val errorUi = ErrorUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    fun assertProgressState() {
        retryButtonUi.assertInvisible()
        errorUi.assertNotVisible()
        progressUi.assertVisible()
    }

    fun waitTilError() {
       errorUi.waitTillVisible()
    }

    fun assertErrorState() {
        retryButtonUi.assertVisible()
        errorUi.assertVisible()
        progressUi.assertNotVisible()
    }

    fun clickRetry() {
        retryButtonUi.click()
    }

    fun waitTillGone() {
        errorUi.waitTillDoesNotExist()
    }
}
