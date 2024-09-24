package ru.german.android.expertcourseunscrambleword.load.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.HttpURLConnection
import java.net.URL

interface LoadRepository {

    fun load(): LoadResult

    class Base(
        private val parseWords: ParseWords,
        private val dataCache: StringCache,
    ) : LoadRepository {

        private val url = "https://random-word-api.herokuapp.com/word?number=10&lang=en"

        override fun load(): LoadResult {
            val connection = URL(url).openConnection() as HttpURLConnection
            try {
                val data = connection.inputStream.bufferedReader().use { it.readText() }
                val response = parseWords.parse(data)

                if (response.isEmpty())
                    return LoadResult.Error("Empty data, try again later")
                else {
                    dataCache.save(data)
                    return LoadResult.Success
                }


            } catch (e: Exception) {
                e.printStackTrace()
                return LoadResult.Error(e.message.toString())
            } finally {
                connection.disconnect()
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
            val data = object : TypeToken<List<String>>(){}.type
            return gson.fromJson(source, data)
        }
    }
}

class Response(val result: List<String>)

