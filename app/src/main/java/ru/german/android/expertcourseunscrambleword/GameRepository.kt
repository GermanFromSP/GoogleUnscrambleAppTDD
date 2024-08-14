package ru.german.android.expertcourseunscrambleword

interface GameRepository {
    fun getUnscrambleWord(): String
    fun getOriginalWord(): String
    fun next()
}