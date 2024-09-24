package ru.german.android.expertcourseunscrambleword.load.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface LoadRepository {

    fun load(): LoadResult

    class Base(
        private val service: WordsService,
        private val parseWords: ParseWords,
        private val dataCache: StringCache,
    ) : LoadRepository {

        override fun load(): LoadResult {

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


//            val connection = URL(url).openConnection() as HttpURLConnection
//            try {
//                val data = connection.inputStream.bufferedReader().use { it.readText() }
//                val response = parseWords.parse(data)
//
//                if (response.isEmpty())
//                    return LoadResult.Error("Empty data, try again later")
//                else {
//                    dataCache.save(data)
//                    return LoadResult.Success
//                }
//
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//                return LoadResult.Error(e.message.toString())
//            } finally {
//                connection.disconnect()
//            }
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

