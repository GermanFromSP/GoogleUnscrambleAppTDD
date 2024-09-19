package ru.german.android.expertcourseunscrambleword.load

import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import ru.german.android.expertcourseunscrambleword.load.data.Response
import java.net.HttpURLConnection
import java.net.URL

class LoadRepositoryTest {

    private val gson = Gson()

    @Test
    fun test() {
        val url = "https://random-word-api.herokuapp.com/word?number=10&lang=en"

        val connection = URL(url).openConnection() as HttpURLConnection
        try {
            val data = connection.inputStream.bufferedReader().use { it.readText() }
            assertTrue(data.isNotEmpty())

            val response = gson.fromJson(data, Response::class.java)
            val list = response.result
            assertEquals(10, list.size)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
    }
}

private class Response(val result: List<String>)