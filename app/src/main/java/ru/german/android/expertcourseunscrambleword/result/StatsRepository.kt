package ru.german.android.expertcourseunscrambleword.result

import ru.german.android.expertcourseunscrambleword.IntCache

interface StatsRepository {
    fun stats(): Pair<Int, Int>
    fun clear()

    class Base(
        private val corrects: IntCache,
        private val incorrect: IntCache
    ) : StatsRepository {

        override fun stats(): Pair<Int, Int> = Pair(corrects.read(), incorrect.read())
        override fun clear() {
            corrects.save(0)
            incorrect.save(0)
        }
    }
}