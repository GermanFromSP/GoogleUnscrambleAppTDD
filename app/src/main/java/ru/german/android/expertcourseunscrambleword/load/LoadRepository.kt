package ru.german.android.expertcourseunscrambleword.load

import androidx.core.view.ContentInfoCompat.Source
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL

interface LoadRepository {

    fun load(resultCallback: (LoadResult) -> Unit)

    class Base(
        private val parseWords: ParseWords,
        private val dataCache: StringCache,
    ) : LoadRepository {

        private val url = "https://random-word-api.herokuapp.com/word?number=10&lang=en"

        override fun load(resultCallback: (LoadResult) -> Unit) {
            val connection = URL(url).openConnection() as HttpURLConnection
            try {
                val data = connection.inputStream.bufferedReader().use { it.readText() }
                val response = parseWords.parse(data)
                val list = response.result

                if (list.isEmpty())
                    resultCallback.invoke(LoadResult.Error("Empty data, try again later"))
                else {
                    dataCache.save(data)
                    resultCallback.invoke(LoadResult.Success)
                }


            } catch (e: Exception) {
                e.printStackTrace()
                resultCallback.invoke(LoadResult.Error(e.message.toString()))
            } finally {
                connection.disconnect()
            }
        }
    }
}

interface ParseWords {

    fun parse(source: String) : Response

    class Base(
        private val gson: Gson
    ) : ParseWords {

        override fun parse(source: String): Response {
            return gson.fromJson(source, Response::class.java)
        }

    }
}

class Response(val result: List<String>)
