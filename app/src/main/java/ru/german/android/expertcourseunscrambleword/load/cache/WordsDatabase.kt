package ru.german.android.expertcourseunscrambleword.load.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WordsCache::class],
    version = 1
)
abstract class WordsDatabase : RoomDatabase(), ClearDatabase {

    override fun clear() = clearAllTables()

    abstract fun getDao() : WordsDao
}

interface ClearDatabase {

    fun clear()
}