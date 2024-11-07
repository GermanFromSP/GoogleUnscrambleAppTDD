package ru.german.android.expertcourseunscrambleword.load.data

import kotlinx.coroutines.delay
import ru.german.android.expertcourseunscrambleword.load.cache.WordsCache
import ru.german.android.expertcourseunscrambleword.load.cache.WordsDao
import ru.german.android.expertcourseunscrambleword.load.net.WordsService

interface LoadRepository {

    suspend fun load(): LoadResult

    class Fake() : LoadRepository {

        private var count = 0

        override suspend fun load(): LoadResult {
            delay(3000)
            return if (count == 0) {
                count++
                LoadResult.Error("Empty data, try again later")
            } else {
                LoadResult.Success
            }
        }
    }

    class Base(
        private val service: WordsService,
        private val dao: WordsDao,
        private val size: Int
    ) : LoadRepository {

        override suspend fun load(): LoadResult {

            try {
                val result = service.getRandomWordList(size).execute()
                val body = result.body()!!

                if (result.isSuccessful) {
                    val data = body.mapIndexed { index, word ->
                        WordsCache(id = index, word = word)
                    }
                    dao.saveWords(data)
                    return LoadResult.Success
                } else {
                    return LoadResult.Error("Empty data, try again later")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                return LoadResult.Error("Empty data, try again later")
            }
        }
    }
}

class Response(val result: List<String>)

