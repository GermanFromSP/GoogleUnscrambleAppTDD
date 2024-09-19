package ru.german.android.expertcourseunscrambleword.load

interface UiObservable {

    fun register(observer: (LoadUiState) -> Unit)

    fun unregister()

    fun postUiState(uiState: LoadUiState)

    class Base() : UiObservable {

        private var observerCached: ((LoadUiState) -> Unit)? = null
        private var uiStateCached: LoadUiState? = null

        override fun register(observer: (LoadUiState) -> Unit) {
            observerCached = observer

            if (uiStateCached != null) {
                observerCached!!.invoke(uiStateCached!!)
                uiStateCached = null
            }

        }

        override fun unregister() {
            observerCached = null
        }

        override fun postUiState(uiState: LoadUiState) {
            if (observerCached == null) {
                uiStateCached = uiState

            } else {
                observerCached!!.invoke(uiState)
                uiStateCached = null
            }
        }

    }
}