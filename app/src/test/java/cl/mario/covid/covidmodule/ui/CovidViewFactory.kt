package cl.mario.covid.covidmodule.ui

import cl.mario.covid.covidmodule.data.models.CovidResultsData
import cl.mario.covid.covidmodule.data.models.InfoData
import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData
import cl.mario.covid.covidmodule.util.getCurrentDateFormatApi

class CovidViewFactory {

    fun getCovidResultViewData(): CovidResultViewData {
        return CovidResultViewData(confirmed = 0, death = 0, date = getCurrentDateFormatApi())
    }

    fun getCovidResultData(): CovidResultsData {
        return CovidResultsData(
            InfoData(
                confirmed = 0,
                deaths = 0,
                date = getCurrentDateFormatApi()
            )
        )
    }
}