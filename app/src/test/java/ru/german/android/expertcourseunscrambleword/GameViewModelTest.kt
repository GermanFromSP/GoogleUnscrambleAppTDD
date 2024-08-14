package ru.german.android.expertcourseunscrambleword

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

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    @Test
    fun caseNumber1() {

        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.InitialState(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "1")
        expected = GameUiState.Insufficient(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "1f")
        expected = GameUiState.Sufficient(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "1f")
        expected = GameUiState.Correct(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.clickNext()
        expected = GameUiState.InitialState(shuffledWord = "f2")
        assertEquals(expected, actual)
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
        expected = GameUiState.Insufficient(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.clickSkip()
        expected = GameUiState.InitialState(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f")
        expected = GameUiState.Insufficient(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "f2")
        expected = GameUiState.Incorrect(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.clickSkip()
        expected = GameUiState.InitialState(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f")
        expected = GameUiState.Insufficient(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "f2")
        expected = GameUiState.Incorrect(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "")
        expected = GameUiState.Insufficient(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f2")
        expected = GameUiState.Sufficient(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "")
        expected = GameUiState.Insufficient(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.checkSufficient(text = "f3")
        expected = GameUiState.Sufficient(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.clickCheck(text = "f3")
        expected = GameUiState.Incorrect(shuffledWord = "f4")
        assertEquals(expected, actual)


    }

    private class FakeRepository : GameRepository {

        private val originalList= listOf("1f", "2f", "3f", "4f")

        private val unscrambledList = originalList.map { it.reversed() }

        private var wordCaseIndex: Int = 0

        override fun getUnscrambleWord(): String = unscrambledList[wordCaseIndex]

        override fun getOriginalWord(): String = originalList[wordCaseIndex]

        override fun next() {
            wordCaseIndex++
            if (wordCaseIndex == originalList.size) wordCaseIndex = 0
        }
    }
}