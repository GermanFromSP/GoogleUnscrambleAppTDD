package ru.german.android.expertcourseunscrambleword.game

import ru.german.android.expertcourseunscrambleword.core.IntCache
import ru.german.android.expertcourseunscrambleword.load.cache.ClearDatabase
import ru.german.android.expertcourseunscrambleword.load.cache.WordsDao

interface GameRepository {
    suspend fun getUnscrambleWord(): String
    suspend fun getOriginalWord(): String
    fun next()
    fun isLastQuestion(): Boolean
    fun clear()
    fun skip()

    class Base(
        private val corrects: IntCache,
        private val incorrect: IntCache,
        private val wordCaseIndex: IntCache,
        private val dao: WordsDao,
        private val clearDatabase: ClearDatabase,
        private val size: Int
    ) : GameRepository {

        override suspend fun getUnscrambleWord(): String {
            val id = wordCaseIndex.read()
            return dao.getWord(id).word.toCharArray().shuffle().toString()
        }

        override suspend fun getOriginalWord(): String {
            val id = wordCaseIndex.read()
            return dao.getWord(id).word
        }

        override fun next() {
            corrects.save(corrects.read() + 1)
            wordCaseIndex.save(wordCaseIndex.read() + 1)
        }

        override fun isLastQuestion(): Boolean {
            return wordCaseIndex.read() == size
        }

        override fun clear() {
            wordCaseIndex.save(0)
            clearDatabase.clear()
        }

        override fun skip() {
            if (!isLastQuestion()) {
                wordCaseIndex.save(wordCaseIndex.read() + 1)
            }
            incorrect.save(incorrect.read() + 1)

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

        override suspend fun getUnscrambleWord(): String = unscrambledList[wordCaseIndex.read()]

        override suspend fun getOriginalWord(): String = listOfOriginal[wordCaseIndex.read()]

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