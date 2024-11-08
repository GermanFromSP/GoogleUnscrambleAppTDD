package ru.german.android.expertcourseunscrambleword.di

import ru.german.android.expertcourseunscrambleword.core.MyViewModel
import ru.german.android.expertcourseunscrambleword.game.di.ProvideGameViewModel
import ru.german.android.expertcourseunscrambleword.load.di.ProvideLoadViewModel
import ru.german.android.expertcourseunscrambleword.result.di.ProvideGameOverViewModel

interface ProvideViewModel {

    fun <S : Any, T : MyViewModel<S>> makeViewModel(clazz: Class<T>): T


    class Make(
        core: Core
    ) : ProvideViewModel {

        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideLoadViewModel(core, chain)
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
        }

        override fun <S : Any, T : MyViewModel<S>> makeViewModel(clazz: Class<T>): T {
            return chain.makeViewModel(clazz)
        }
    }


    class Error : ProvideViewModel {

        override fun <S : Any, T : MyViewModel<S>> makeViewModel(clazz: Class<T>): T {
            throw IllegalStateException("unknown class $clazz")
        }
    }
}

