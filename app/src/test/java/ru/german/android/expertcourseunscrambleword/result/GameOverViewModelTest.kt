package ru.german.android.expertcourseunscrambleword.result

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.german.android.expertcourseunscrambleword.views.stats.StatsUiState


class GameOverViewModelTest {

    @Test
    fun test() {

        val repository = FakeRepository()
        val viewModel = GameOverViewModel(repository = repository)

        assertEquals(StatsUiState.Base(2,2), viewModel.getStatsUiState())
    }
}

private class FakeRepository : StatsRepository {

    override fun stats(): Pair<Int, Int> = Pair(2, 2)

}
