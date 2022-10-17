package cl.mario.covid.covidmodule.data.remote

import cl.mario.covid.covidmodule.data.models.CovidResultsData
import cl.mario.covid.covidmodule.util.Constants.Companion.TOTAL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidApi {
    @GET(TOTAL)
    suspend fun getData(@Query("date") date: String): Response<CovidResultsData>
}