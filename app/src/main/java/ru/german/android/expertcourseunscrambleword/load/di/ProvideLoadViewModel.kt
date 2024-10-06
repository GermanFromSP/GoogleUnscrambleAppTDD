package ru.german.android.expertcourseunscrambleword.load.di

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.german.android.expertcourseunscrambleword.RunAsync
import ru.german.android.expertcourseunscrambleword.di.AbstractProvideViewModel
import ru.german.android.expertcourseunscrambleword.di.Core
import ru.german.android.expertcourseunscrambleword.di.Module
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.load.data.LoadRepository
import ru.german.android.expertcourseunscrambleword.load.data.ParseWords
import ru.german.android.expertcourseunscrambleword.load.data.Response
import ru.german.android.expertcourseunscrambleword.load.data.StringCache
import ru.german.android.expertcourseunscrambleword.load.net.WordsService
import ru.german.android.expertcourseunscrambleword.load.presentation.LoadViewModel
import ru.german.android.expertcourseunscrambleword.load.presentation.UiObservable

class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LoadViewModel::class.java) {
    override fun module(): Module<*> = LoadModule(core)
}

class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        val response = Response(listOf())
        val defaultResponse = core.gson.toJson(response)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://random-word-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WordsService::class.java)

        return LoadViewModel(
            repository =
            if (core.runUiTests)
                LoadRepository.Fake()
            else
                LoadRepository.Base(
                    service,
                    ParseWords.Base(Gson()),
                    StringCache.Base(core.sharedPreferences, "response_data", defaultResponse)
                ),
            observable = UiObservable.Base(),
            runAsync = RunAsync.Base(),
            core.clearViewModel
        )
    }
}