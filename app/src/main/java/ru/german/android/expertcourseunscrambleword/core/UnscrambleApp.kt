package ru.german.android.expertcourseunscrambleword.core

import android.app.Application
import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.di.Core
import ru.german.android.expertcourseunscrambleword.di.ManageViewModels
import ru.german.android.expertcourseunscrambleword.di.ProvideViewModel

class UnscrambleApp : Application(), ProvideViewModel {

    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()
        val make = ProvideViewModel.Make(
            core = Core(
                context = this,
                clearViewModel = object : ClearViewModel {
                    override fun clear(viewModelClass: Class<out MyViewModel<*>>) =
                        factory.clear(viewModelClass)
                }
            )
        )

        factory = ManageViewModels.Factory(make)
    }

    override fun <S : Any, T : MyViewModel<S>> makeViewModel(clazz: Class<T>): T =
        factory.makeViewModel(clazz)

}

