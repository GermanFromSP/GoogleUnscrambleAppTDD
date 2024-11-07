package ru.german.android.expertcourseunscrambleword.load.cache

import android.content.Context
import androidx.room.Room
import ru.german.android.expertcourseunscrambleword.R

interface CacheModule {

    fun dao(): WordsDao

    fun clearDatabase(): ClearDatabase

    class Base(applicationContext: Context) : CacheModule {

        private val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                WordsDatabase::class.java, applicationContext.getString(R.string.app_name)
            ).build()
        }

        override fun dao(): WordsDao = database.getDao()

        override fun clearDatabase(): ClearDatabase = database
    }

}