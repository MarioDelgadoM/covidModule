package cl.mario.covid.covidmodule

import cl.mario.covid.covidmodule.data.useCases.GetDataCovidUseCase
import cl.mario.covid.covidmodule.ui.main.CovidViewModel
import cl.mario.covid.covidmodule.util.getCurrentDateFormatApi
import cl.mario.covid.covidmodule.util.getCurrentDateWithSpanishFormat
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CovidViewModelTest {
    @RelaxedMockK
    private lateinit var getDataCovidUseCase: GetDataCovidUseCase

    @RelaxedMockK
    private lateinit var covidViewModel: CovidViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        covidViewModel = CovidViewModel(getDataCovidUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when covidviewmodel is calling at the first time of app then set the lastdaterequest`() =
        runTest {
            //Given
            val lastDate = getCurrentDateWithSpanishFormat(
                getCurrentDateFormatApi()
            )
            //When
            covidViewModel.getCovidResults(getCurrentDateWithSpanishFormat())
            //Then
            assert(
                covidViewModel.lastDateRequest == lastDate
            )
        }
}