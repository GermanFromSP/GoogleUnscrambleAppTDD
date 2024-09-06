package ru.german.android.expertcourseunscrambleword.game

import ru.german.android.expertcourseunscrambleword.IntCache

interface GameRepository {
    fun getUnscrambleWord(): String
    fun getOriginalWord(): String
    fun next()
    fun isLastQuestion(): Boolean
    fun clear()
    fun skip()

    data class Base(
        private val corrects: IntCache,
        private val incorrect: IntCache,
        private val listOfOriginal: List<String> = listOf(
            "bluetooth", "processor", "drone", "light", "tripple"
        ),
        private val wordCaseIndex: IntCache
    ) : GameRepository {

        private val unscrambledList = listOfOriginal.map { it.reversed() }

        override fun getUnscrambleWord(): String = unscrambledList[wordCaseIndex.read()]

        override fun getOriginalWord(): String = listOfOriginal[wordCaseIndex.read()]

        override fun next() {

            if (!isLastQuestion()) {
                wordCaseIndex.save(wordCaseIndex.read() + 1)
                corrects.save(corrects.read() + 1)
            }

        }

        override fun isLastQuestion(): Boolean = wordCaseIndex.read() == listOfOriginal.size
        override fun clear() {
            wordCaseIndex.save(0)
        }

        override fun skip() {
            if (!isLastQuestion()) {
                wordCaseIndex.save(wordCaseIndex.read() + 1)
            }
            incorrect.save(incorrect.read() + 1)
        }
    }
}