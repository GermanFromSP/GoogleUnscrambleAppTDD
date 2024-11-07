package ru.german.android.expertcourseunscrambleword.load.presentation

interface LoadUiObservable : UiObservable<LoadUiState> {

    class Abstract : UiObservable.Abstract<LoadUiState>(), LoadUiObservable
}