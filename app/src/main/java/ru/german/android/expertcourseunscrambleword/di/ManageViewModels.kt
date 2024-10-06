package ru.german.android.expertcourseunscrambleword.di

import ru.german.android.expertcourseunscrambleword.core.MyViewModel

interface ManageViewModels : ProvideViewModel, ClearViewModel {

    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {

        private val viewModelMap = mutableMapOf<Class<out MyViewModel<*>>, MyViewModel<*>?>()

        override fun <S : Any, T : MyViewModel<S>> makeViewModel(clazz: Class<T>): T =
            if (viewModelMap[clazz] == null) {
                val viewModel = make.makeViewModel(clazz)
                viewModelMap[clazz] = viewModel
                viewModel
            } else viewModelMap[clazz] as T

        override fun clear(viewModelClass: Class<out MyViewModel<*>>) {
            viewModelMap[viewModelClass] = null
        }
    }
}