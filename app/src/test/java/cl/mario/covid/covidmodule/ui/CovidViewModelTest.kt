package cl.mario.covid.covidmodule.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import cl.mario.covid.covidmodule.data.useCases.GetDataCovidUseCase
import cl.mario.covid.covidmodule.ui.main.CovidViewModel
import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData
import cl.mario.covid.covidmodule.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.refEq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CovidViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getDataCovidUseCase: GetDataCovidUseCase

    @Mock
    lateinit var covidViewModel: CovidViewModel

    @Mock
    lateinit var covidStateObserver: Observer<State<CovidResultViewData>>

    @Before
    fun onBefore() {
        MockitoAnnotations.openMocks(this)

        covidViewModel = CovidViewModel(getDataCovidUseCase).apply {
            covidInfoStateLiveData.observeForever(covidStateObserver)
        }

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun should_changeLoadingState_when_getCovidResults() = runTest {

        val dateCallApi = CovidViewFactory().getDateForCallApi()

        whenever(getDataCovidUseCase.execute(dateCallApi)).thenReturn(
            flowOf(State.loading())
        )

        covidViewModel.getCovidResults()

        verify(covidStateObserver).onChanged(refEq(State.loading()))
    }

    @Test
    fun should_changeSuccessState_when_getCovidResultsIsOk() = runTest {

        val covidResultViewData = CovidViewFactory().getCovidResultViewData()
        val dateCallApi = CovidViewFactory().getDateForCallApi()

        whenever(getDataCovidUseCase.execute(dateCallApi)).thenReturn(
            flowOf(State.success(covidResultViewData))
        )

        covidViewModel.getCovidResults()

        verify(covidStateObserver).onChanged(refEq(State.success(covidResultViewData)))
    }

    @Test(expected = Exception::class)
    fun should_changeErrorState_when_getCovidResultsErrorApi() = runTest {

        val dateCallApi = CovidViewFactory().getDateForCallApi()
        val throwableError = Throwable(message = "State error")

        whenever(getDataCovidUseCase.execute(dateCallApi)).thenReturn(
            flowOf(State.loading())
        ).thenThrow(throwableError)

        covidViewModel.getCovidResults()

        verify(covidStateObserver).onChanged(refEq(State.loading()))
        verify(covidStateObserver).onChanged(refEq(State.error("State error")))
    }

    @Test
    fun should_setLastDateRequest_when_getCovidResults() = runTest {

        val covidResultViewData = CovidViewFactory().getCovidResultViewData()
        val dateCallApi = CovidViewFactory().getDateForCallApi()

        whenever(getDataCovidUseCase.execute(dateCallApi)).thenReturn(
            flowOf(State.success(covidResultViewData))
        )

        covidViewModel.getCovidResults()

        assert(covidViewModel.lastDateRequest == dateCallApi)
    }

}