package ru.german.android.expertcourseunscrambleword.load.di

import com.google.gson.Gson
import ru.german.android.expertcourseunscrambleword.di.AbstractProvideViewModel
import ru.german.android.expertcourseunscrambleword.di.Core
import ru.german.android.expertcourseunscrambleword.di.Module
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel
import ru.german.android.expertcourseunscrambleword.load.data.LoadRepository
import ru.german.android.expertcourseunscrambleword.load.data.ParseWords
import ru.german.android.expertcourseunscrambleword.load.data.Response
import ru.german.android.expertcourseunscrambleword.load.data.StringCache
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

        return LoadViewModel(
            repository = LoadRepository.Base(
                ParseWords.Base(Gson()),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse)
            ),
            observable = UiObservable.Base()
        )
    }
}