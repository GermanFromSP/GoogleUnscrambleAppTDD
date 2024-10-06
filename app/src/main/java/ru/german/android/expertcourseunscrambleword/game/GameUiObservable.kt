package ru.german.android.expertcourseunscrambleword.game

import ru.german.android.expertcourseunscrambleword.load.presentation.UiObservable

interface GameUiObservable : UiObservable<GameUiState> {

    class Base : UiObservable.Abstract<GameUiState>(), GameUiObservable
}