package ru.german.android.expertcourseunscrambleword.load

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.german.android.expertcourseunscrambleword.RunAsync
import ru.german.android.expertcourseunscrambleword.di.ClearViewModel
import ru.german.android.expertcourseunscrambleword.game.GameViewModelTest
import ru.german.android.expertcourseunscrambleword.load.data.LoadRepository
import ru.german.android.expertcourseunscrambleword.load.data.LoadResult
import ru.german.android.expertcourseunscrambleword.load.presentation.LoadUiState
import ru.german.android.expertcourseunscrambleword.load.presentation.LoadViewModel
import ru.german.android.expertcourseunscrambleword.load.presentation.UiObservable

class LoadViewModelTest {

    private lateinit var repository: FakeLoadRepository
    private lateinit var observable: FakeUiObservable
    private lateinit var runAsync: FakeRunAsync
    private lateinit var viewModel: LoadViewModel
    private lateinit var clearViewModel: GameViewModelTest.FakeClearViewModel

    @Before
    fun setup() {
        repository = FakeLoadRepository()
        observable = FakeUiObservable()
        runAsync = FakeRunAsync()
        clearViewModel = GameViewModelTest.FakeClearViewModel()
        viewModel = LoadViewModel(repository, observable, runAsync, clearViewModel)
    }

    @Test
    fun sameFragment() {
        repository.expectResult(LoadResult.Success)
        val fragment = FakeFragment()

        viewModel.load(isFirstRun = true)
        assertEquals(LoadUiState.Progress, observable.postUiStateCalledList.first())
        assertEquals(1, observable.postUiStateCalledList.size)
        assertEquals(1, repository.loadCalledCount)

        viewModel.startUpdates(observer = fragment)
        assertEquals(1, observable.registerCalledCount)

        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        runAsync.returnResult()
        assertEquals(LoadUiState.Success, observable.postUiStateCalledList[1])
        assertEquals(2, observable.postUiStateCalledList.size)
        assertEquals(LoadUiState.Success, fragment.statesList[1])
        assertEquals(2, fragment.statesList.size)
        clearViewModel.assertClearCalled(LoadViewModel::class.java)
    }

    @Test
    fun recreateActivity() {
        repository.expectResult(LoadResult.Error(message = "No internet"))
        val fragment = FakeFragment()

        viewModel.load(isFirstRun = true)
        assertEquals(LoadUiState.Progress, observable.postUiStateCalledList.first())
        assertEquals(1, observable.postUiStateCalledList.size)
        assertEquals(1, repository.loadCalledCount)

        viewModel.startUpdates(observer = fragment)
        assertEquals(1, observable.registerCalledCount)

        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        viewModel.stopUpdates()
        assertEquals(1, observable.unregisterCalledCount)

        runAsync.returnResult()
        assertEquals(1, fragment.statesList.size)
        assertEquals(
            LoadUiState.Error(message = "No internet"),
            observable.postUiStateCalledList[1]
        )
        assertEquals(2, observable.postUiStateCalledList.size)

        val newFragment = FakeFragment()
        repository.expectResult(LoadResult.Success)

        viewModel.load(isFirstRun = false)
        assertEquals(1, repository.loadCalledCount)

        viewModel.startUpdates(observer = newFragment)
        assertEquals(2, observable.registerCalledCount)

        assertEquals(LoadUiState.Error(message = "No internet"), newFragment.statesList.first())
        assertEquals(1, newFragment.statesList.size)

        viewModel.load()

        assertEquals(LoadUiState.Progress, observable.postUiStateCalledList.first())
        assertEquals(3, observable.postUiStateCalledList.size)
        assertEquals(2, repository.loadCalledCount)

        viewModel.startUpdates(observer = newFragment)
        assertEquals(3, observable.registerCalledCount)

        assertEquals(LoadUiState.Progress, newFragment.statesList[1])
        assertEquals(2, newFragment.statesList.size)

        runAsync.returnResult()
        assertEquals(LoadUiState.Success, observable.postUiStateCalledList[3])
        assertEquals(4, observable.postUiStateCalledList.size)
        assertEquals(LoadUiState.Success, newFragment.statesList.last())
        assertEquals(3, newFragment.statesList.size)

    }
}

private class FakeFragment : (LoadUiState) -> Unit {

    val statesList = mutableListOf<LoadUiState>()

    override fun invoke(p1: LoadUiState) {
        statesList.add(p1)
    }
}

private class FakeLoadRepository : LoadRepository {

    private var loadResult: LoadResult? = null

    fun expectResult(loadResult: LoadResult) {
        this.loadResult = loadResult

    }

    var loadCalledCount = 0

    override suspend fun load(): LoadResult {

        loadCalledCount++
        return loadResult!!

    }
}

private class FakeUiObservable : UiObservable {

    private var observerCached: ((LoadUiState) -> Unit)? = null
    private var uiStateCached: LoadUiState? = null

    var registerCalledCount = 0

    override fun register(observer: (LoadUiState) -> Unit) {
        registerCalledCount++
        observerCached = observer

        if (uiStateCached != null) {
            observerCached!!.invoke(uiStateCached!!)
            uiStateCached = null
        }
    }

    var unregisterCalledCount = 0

    override fun unregister() {
        unregisterCalledCount++
        observerCached = null
    }

    val postUiStateCalledList = mutableListOf<LoadUiState>()

    override fun postUiState(uiState: LoadUiState) {
        postUiStateCalledList.add(uiState)
        if (observerCached == null) {
            uiStateCached = uiState

        } else {
            observerCached!!.invoke(uiState)
            uiStateCached = null
        }
    }
}

private class FakeRunAsync() : RunAsync {

    private var result: Any? = null
    private var ui: (Any) -> Unit = {}

    override fun <T : Any> handleAsync(
        coroutineScope: CoroutineScope,
        heavyOperation: suspend () -> T,
        uiUpdate: (T) -> Unit
    ) = runBlocking {
        result = heavyOperation.invoke()
        ui = uiUpdate as (Any) -> Unit
    }

    fun returnResult() {
        ui.invoke(result!!)
    }
}