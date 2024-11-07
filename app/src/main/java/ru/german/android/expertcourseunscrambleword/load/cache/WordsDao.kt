package ru.german.android.expertcourseunscrambleword.load.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordsDao {

    @Insert
    suspend fun saveWords(words: List<WordsCache>)

    @Query("select * from words where id=:id")
    suspend fun getWord(id: Int): WordsCache

}