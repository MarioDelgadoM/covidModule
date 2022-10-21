package cl.mario.covid.covidmodule.data.useCases

import cl.mario.covid.covidmodule.data.mappers.CovidResultMapper
import cl.mario.covid.covidmodule.data.remote.CovidApi
import cl.mario.covid.covidmodule.util.getCurrentDateFormatApi
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDataCovidUseCaseTest {
    @RelaxedMockK
    lateinit var getDataCovidUseCase: GetDataCovidUseCase

    @RelaxedMockK
    lateinit var covidApi: CovidApi

    @RelaxedMockK
    lateinit var covidResultMapper: CovidResultMapper

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDataCovidUseCase = GetDataCovidUseCase(covidApi, covidResultMapper)
    }

    @Test
    fun `when execute the usecase with the default date then should be call the api just once`() =
        runBlocking {
            getDataCovidUseCase.execute(getCurrentDateFormatApi()).collect {}
            coVerify(exactly = 1) { covidApi.getData(getCurrentDateFormatApi()) }
        }
}