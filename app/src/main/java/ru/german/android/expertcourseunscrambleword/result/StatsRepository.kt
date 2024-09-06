package ru.german.android.expertcourseunscrambleword.result

import ru.german.android.expertcourseunscrambleword.IntCache

interface StatsRepository {
    fun stats(): Pair<Int, Int>

    class Base(
        private val corrects: IntCache,
        private val incorrect: IntCache
    ) : StatsRepository {

        override fun stats(): Pair<Int, Int> = Pair(corrects.read(), incorrect.read())
    }
}