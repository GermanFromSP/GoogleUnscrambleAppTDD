package ru.german.android.expertcourseunscrambleword.load

interface LoadRepository {

    fun load(resultCallback: (LoadResult) -> Unit)
}
