package ru.german.android.expertcourseunscrambleword.load.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.german.android.expertcourseunscrambleword.core.RunAsync
import ru.german.android.expertcourseunscrambleword.di.AbstractProvideViewModel
import ru.german.android.expertcourseunscrambleword.di.Core
import ru.german.android.expertcourseunscrambleword.di.Module
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.load.data.LoadRepository
import ru.german.android.expertcourseunscrambleword.load.data.Response
import ru.german.android.expertcourseunscrambleword.load.net.WordsService
import ru.german.android.expertcourseunscrambleword.load.presentation.LoadUiObservable
import ru.german.android.expertcourseunscrambleword.load.presentation.LoadViewModel

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
                    core.cacheModule.dao(),
                    core.size
                ),
            observable = LoadUiObservable.Abstract(),
            runAsync = RunAsync.Base(),
            core.clearViewModel
        )
    }
}