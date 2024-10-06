package ru.german.android.expertcourseunscrambleword.load.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordsCache(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("word")
    val word: String
) {
}