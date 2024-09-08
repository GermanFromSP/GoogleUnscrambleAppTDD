package ru.german.android.expertcourseunscrambleword.di

import ru.german.android.expertcourseunscrambleword.result.di.GameOverModule
import ru.german.android.expertcourseunscrambleword.MyViewModel
import ru.german.android.expertcourseunscrambleword.game.GameViewModel
import ru.german.android.expertcourseunscrambleword.game.di.GameModule
import ru.german.android.expertcourseunscrambleword.game.di.ProvideGameViewModel
import ru.german.android.expertcourseunscrambleword.result.GameOverViewModel
import ru.german.android.expertcourseunscrambleword.result.di.ProvideGameOverViewModel

interface ProvideViewModel {

    fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T


    class Make(
        core: Core
    ) : ProvideViewModel {

        private var chain: ProvideViewModel = ProvideViewModel.Error()

        init {
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
        }

        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
            return chain.makeViewModel(clazz)
        }
    }


    class Error : ProvideViewModel {

        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
            throw IllegalStateException("unknown class $clazz")
        }
    }
}

