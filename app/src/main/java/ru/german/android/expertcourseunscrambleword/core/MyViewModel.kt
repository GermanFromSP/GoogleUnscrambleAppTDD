package ru.german.android.expertcourseunscrambleword.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.german.android.expertcourseunscrambleword.load.presentation.UiObservable

interface MyViewModel<T: Any> {

    fun startUpdates(observer: (T) -> Unit)

    fun stopUpdates()

    abstract class Abstract<T : Any>(
        protected val observable: UiObservable<T>
    ) : MyViewModel<T> {

        protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

       override fun startUpdates(observer: (T) -> Unit) = observable.register(observer)

       override fun stopUpdates() = observable.unregister()

    }
}



