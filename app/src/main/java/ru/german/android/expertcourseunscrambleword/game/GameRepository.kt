package ru.german.android.expertcourseunscrambleword.game

import ru.german.android.expertcourseunscrambleword.IntCache
import ru.german.android.expertcourseunscrambleword.load.cache.ClearDatabase
import ru.german.android.expertcourseunscrambleword.load.cache.WordsDao

interface GameRepository {
    fun getUnscrambleWord(): String
    fun getOriginalWord(): String
    fun next()
    fun isLastQuestion(): Boolean
    fun clear()
    fun skip()

    class Base(
        private val corrects: IntCache,
        private val incorrect: IntCache,
        private val wordCaseIndex: IntCache,
        private val dao: WordsDao,
        private val clearDatabase: ClearDatabase
    ) : GameRepository {
        override fun getUnscrambleWord(): String {
            TODO("Not yet implemented")
        }

        override fun getOriginalWord(): String {
            TODO("Not yet implemented")
        }

        override fun next() {
            TODO("Not yet implemented")
        }

        override fun isLastQuestion(): Boolean {
            TODO("Not yet implemented")
        }

        override fun clear() {
            TODO("Not yet implemented")
        }

        override fun skip() {
            TODO("Not yet implemented")
        }

    }

    data class Fake(
        private val corrects: IntCache,
        private val incorrect: IntCache,
        private val wordCaseIndex: IntCache,
        private val listOfOriginal: List<String> = listOf(
            "bluetooth", "processor", "drone", "light", "tripple"
        )
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