package ru.german.android.expertcourseunscrambleword.game

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.german.android.expertcourseunscrambleword.core.MyViewModel
import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.load.FakeRunAsync
import ru.german.android.expertcourseunscrambleword.load.FakeRunAsyncImmediate
import ru.german.android.expertcourseunscrambleword.load.FakeUiObservable

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameViewModelTest {

    private lateinit var runAsync: FakeRunAsyncImmediate
    private lateinit var observable: FakeGameUiObservable
    private lateinit var viewModel: GameViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setup() {
        runAsync = FakeRunAsyncImmediate()
        observable = FakeGameUiObservable.Base()
        repository = FakeRepository()
        viewModel = GameViewModel(
            clearViewModel = FakeClearViewModel(),
            repository = repository,
            runAsync = runAsync,
            uiObservable = observable
        )
    }

    @Test
    fun caseNumber1() {

        viewModel.init()
        var actual: GameUiState = observable.postUiStateCalledList.last()
        var expected: GameUiState = GameUiState.InitialState(shuffledWord = "f1")
        assertEquals(expected, actual)

        viewModel.checkSufficient("1")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        viewModel.checkSufficient("1f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickCheck("1f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        viewModel.clickNext()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.InitialState(shuffledWord = "f2")
        assertEquals(expected, actual)

        viewModel.checkSufficient("2f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickCheck("2f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        viewModel.clickNext()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.InitialState(shuffledWord = "f3")
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "3f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickCheck(text = "3f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        viewModel.clickNext()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.InitialState(shuffledWord = "f4")
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "4f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickCheck(text = "4f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        viewModel.clickNext()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Finish
        assertEquals(expected, actual)
        assertEquals(true, repository.clearCalled)
    }

    @Test
    fun caseNumber2() {

        viewModel.init()
        var actual: GameUiState = observable.postUiStateCalledList.last()
        var expected: GameUiState = GameUiState.InitialState(shuffledWord = "f1")
        assertEquals(expected, actual)

        viewModel.clickSkip()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.InitialState(shuffledWord = "f2")
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f2")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickSkip()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.InitialState(shuffledWord = "f3")
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f2")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickCheck(text = "f2")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)

        viewModel.clickSkip()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.InitialState(shuffledWord = "f4")
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f2")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickCheck(text = "f2")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)
        assertEquals(false, repository.clearCalled)

        viewModel.checkSufficient(text = "")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f2")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        viewModel.checkSufficient(text = "f3")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        viewModel.clickCheck(text = "f3")
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)

        viewModel.clickSkip()
        actual = observable.postUiStateCalledList.last()
        expected = GameUiState.Finish
        assertEquals(expected, actual)
        assertEquals(true, repository.clearCalled)


    }

    private class FakeRepository : GameRepository {

        private val originalList = listOf("1f", "2f", "3f", "4f")

        private val unscrambledList = originalList.map { it.reversed() }

        private var wordCaseIndex: Int = 0

        override suspend fun getUnscrambleWord(): String = unscrambledList[wordCaseIndex]

        override suspend fun getOriginalWord(): String = originalList[wordCaseIndex]

        override fun next() {
            wordCaseIndex++
        }

        override fun isLastQuestion(): Boolean = wordCaseIndex == originalList.size

        var clearCalled = false

        override fun clear() {
            clearCalled = true
        }

        override fun skip() {
            next()
        }
    }

    class FakeClearViewModel : ClearViewModel {
        private var actual: Class<out MyViewModel<*>>? = null
        override fun clear(viewModelClass: Class<out MyViewModel<*>>) {
            actual = viewModelClass
        }

        fun assertClearCalled(expected: Class<out MyViewModel<*>>) {
            assertEquals(expected, actual)
        }
    }

    private interface FakeGameUiObservable : FakeUiObservable<GameUiState>, GameUiObservable {

        class Base : FakeGameUiObservable, FakeUiObservable.Abstract<GameUiState>()
    }
}