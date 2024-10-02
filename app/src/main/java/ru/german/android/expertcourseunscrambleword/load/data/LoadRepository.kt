package ru.german.android.expertcourseunscrambleword.load.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay

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
        private val parseWords: ParseWords,
        private val dataCache: StringCache,
    ) : LoadRepository {

        override suspend fun load(): LoadResult {

            try {
                val result = service.getRandomWordList().execute()
                val data = parseWords.parse(result.body()!!.toString()).toString()

                if (result.isSuccessful) {
                    dataCache.save(data)
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

interface ParseWords {

    fun parse(source: String): List<String>

    class Base(
        private val gson: Gson
    ) : ParseWords {

        override fun parse(source: String): List<String> {
            val data = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(source, data)
        }
    }
}

class Response(val result: List<String>)

