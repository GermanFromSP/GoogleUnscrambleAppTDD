package ru.german.android.expertcourseunscrambleword.game

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameViewModelTest {

    private lateinit var viewModel: GameViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setup() {
        repository = FakeRepository()
        viewModel = GameViewModel(repository = repository)
    }

    @Test
    fun caseNumber1() {

        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.InitialState(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "1")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "1f")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "1f")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.clickNext()
        expected = GameUiState.InitialState(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "2f")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "2f")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.clickNext()
        expected = GameUiState.InitialState(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "3f")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "3f")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.clickNext()
        expected = GameUiState.InitialState(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "4f")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "4f")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.clickNext()
        expected = GameUiState.Finish
        assertEquals(expected, actual)
        assertEquals(true, repository.clearCalled)
    }

    @Test
    fun caseNumber2() {

        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.InitialState(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.clickSkip()
        expected = GameUiState.InitialState(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickSkip()
        expected = GameUiState.InitialState(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "f2")
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)

        actual = viewModel.clickSkip()
        expected = GameUiState.InitialState(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "f2")
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)
        assertEquals(false, repository.clearCalled)

        actual = viewModel.checkSufficient(text = "")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f3")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "f3")
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)

        actual = viewModel.clickSkip()
        expected = GameUiState.Finish
        assertEquals(expected, actual)
        assertEquals(true, repository.clearCalled)


    }

    private class FakeRepository : GameRepository {

        private val originalList = listOf("1f", "2f", "3f", "4f")

        private val unscrambledList = originalList.map { it.reversed() }

        private var wordCaseIndex: Int = 0

        override fun getUnscrambleWord(): String = unscrambledList[wordCaseIndex]

        override fun getOriginalWord(): String = originalList[wordCaseIndex]

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
}