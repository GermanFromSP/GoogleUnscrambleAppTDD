package ru.german.android.expertcourseunscrambleword.load

import org.junit.Assert.assertEquals
import org.junit.Test

class LoadViewModelTest {

    @Test
    fun sameFragment() {
        val repository = FakeLoadRepository()
        repository.expectResult(LoadResult.Success)
        val observable = FakeUiObservable()
        val viewModel = LoadViewModel(
            repository = repository,
            observable = observable
        )

        val fragment = FakeFragment()

        viewModel.load(isFirstRun = true)
        assertEquals(LoadUiState.Progress, observable.postUiStateCalledList.first())
        assertEquals(1, observable.postUiStateCalledList.size)
        assertEquals(1, repository.loadCalledCount)

        viewModel.startUpdates(observer = fragment)
        assertEquals(1, observable.registerCalledCount)

        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        repository.returnResult()
        assertEquals(LoadUiState.Success, observable.postUiStateCalledList[1])
        assertEquals(2, observable.postUiStateCalledList.size)
        assertEquals(LoadUiState.Success, fragment.statesList[1])
        assertEquals(2, fragment.statesList.size)
    }

    @Test
    fun recreateActivity() {
        val repository = FakeLoadRepository()
        repository.expectResult(LoadResult.Error(message = "No internet"))
        val observable = FakeUiObservable()
        val viewModel = LoadViewModel(
            repository = repository,
            observable = observable
        )

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

        repository.returnResult()
        assertEquals(1, fragment.statesList.size)
        assertEquals(
            LoadUiState.Error(message = "No internet"),
            observable.postUiStateCalledList[1]
        )
        assertEquals(2, observable.postUiStateCalledList.size)

        val newFragment = FakeFragment()

        viewModel.load(isFirstRun = false)
        assertEquals(1, repository.loadCalledCount)

        viewModel.startUpdates(observer = newFragment)
        assertEquals(2, observable.registerCalledCount)

        assertEquals(LoadUiState.Error(message = "No internet"), newFragment.statesList.first())
        assertEquals(1, newFragment.statesList.size)

        viewModel.load()
        repository.expectResult(LoadResult.Success)
        assertEquals(LoadUiState.Progress, observable.postUiStateCalledList.first())
        assertEquals(3, observable.postUiStateCalledList.size)
        assertEquals(2, repository.loadCalledCount)

        viewModel.startUpdates(observer = newFragment)
        assertEquals(3, observable.registerCalledCount)

        assertEquals(LoadUiState.Progress, newFragment.statesList[1])
        assertEquals(2, newFragment.statesList.size)

        repository.returnResult()
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
    private var loadResultCallback: (LoadResult) -> Unit = {}

    fun expectResult(loadResult: LoadResult) {
        this.loadResult = loadResult

    }

    var loadCalledCount = 0

    override fun load(resultCallback: (LoadResult) -> Unit) {

        loadCalledCount++
        loadResultCallback = resultCallback

    }

    fun returnResult() {
        loadResultCallback.invoke(loadResult!!)
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