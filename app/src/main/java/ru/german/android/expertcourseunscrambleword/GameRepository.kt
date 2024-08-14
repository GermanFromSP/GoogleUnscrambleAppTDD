package ru.german.android.expertcourseunscrambleword

interface GameRepository {
    fun getUnscrambleWord(): String
    fun getOriginalWord(): String
    fun next()

    data class Base(
        private val listOfOriginal: List<String> = listOf(
            "bluetooth", "processor", "drone", "light", "tripple"
        )
    ) : GameRepository {

        private val unscrambledList = listOfOriginal.map { it.reversed() }

        private var wordCaseIndex: Int = 0


        override fun getUnscrambleWord(): String = unscrambledList[wordCaseIndex]

        override fun getOriginalWord(): String = listOfOriginal[wordCaseIndex]

        override fun next() {
            wordCaseIndex++
            if (wordCaseIndex == listOfOriginal.size) wordCaseIndex = 0
        }

    }
}