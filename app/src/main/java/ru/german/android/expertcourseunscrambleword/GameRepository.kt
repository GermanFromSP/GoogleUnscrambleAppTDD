package ru.german.android.expertcourseunscrambleword

interface GameRepository {
    fun getUnscrambleWord(): String
    fun getOriginalWord(): String
    fun next()
    fun isLastQuestion(): Boolean

    data class Base(
        private val listOfOriginal: List<String> = listOf(
            "bluetooth", "processor", "drone", "light", "tripple"
        ),
        private val wordCaseIndex: IntCache
    ) : GameRepository {

        private val unscrambledList = listOfOriginal.map { it.reversed() }

        override fun getUnscrambleWord(): String = unscrambledList[wordCaseIndex.read()]

        override fun getOriginalWord(): String = listOfOriginal[wordCaseIndex.read()]

        override fun next() {

            wordCaseIndex.save(if (isLastQuestion()) (0) else wordCaseIndex.read() + 1)

        }

        override fun isLastQuestion(): Boolean = wordCaseIndex.read() == listOfOriginal.size
    }
}