package ru.german.android.expertcourseunscrambleword.load

interface LoadResult {

    fun isSuccessful(): Boolean

    fun message(): String

    data class Error(private val message: String) : LoadResult {
        override fun isSuccessful(): Boolean {
            return false
        }

        override fun message(): String {
            return message
        }
    }

    object Success : LoadResult {

        override fun isSuccessful(): Boolean = true

        override fun message(): String = throw IllegalStateException("cannot happen")
    }
}