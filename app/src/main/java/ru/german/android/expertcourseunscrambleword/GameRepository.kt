package ru.german.android.expertcourseunscrambleword

interface GameRepository {
    fun getUnscrambleWord(): String
    fun getOriginalWord(): String
    fun next()

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
            wordCaseIndex.save(wordCaseIndex.read() + 1)
            if (wordCaseIndex.read() == listOfOriginal.size) wordCaseIndex.save(0)
        }
    }
}