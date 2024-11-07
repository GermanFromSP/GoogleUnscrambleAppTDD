package ru.german.android.expertcourseunscrambleword

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.german.android.expertcourseunscrambleword.load.cache.WordsCache
import ru.german.android.expertcourseunscrambleword.load.cache.WordsDao
import ru.german.android.expertcourseunscrambleword.load.cache.WordsDatabase

@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var dao: WordsDao
    private lateinit var database: WordsDatabase

    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, WordsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.getDao()

    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun test() = runBlocking {
        dao.saveWords(
            listOf(
                WordsCache(1, "1"),
                WordsCache(2, "2"),
            )
        )

        var actual = dao.getWord(1)
        var expected = WordsCache(1, "1")
        assertEquals(actual, expected)

        actual = dao.getWord(2)
        expected = WordsCache(2, "2")
        assertEquals(actual, expected)
    }
}