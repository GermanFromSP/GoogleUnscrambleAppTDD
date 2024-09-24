package ru.german.android.expertcourseunscrambleword.load.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsService {

    @GET("api")
    fun getRandomWordList(
        @Query("words") listSize: Int = 10,
    ): Call<List<String>>

}