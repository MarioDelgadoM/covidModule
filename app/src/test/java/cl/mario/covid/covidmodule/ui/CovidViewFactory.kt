package cl.mario.covid.covidmodule.ui

import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData
import cl.mario.covid.covidmodule.util.getCurrentDateFormatApi

class CovidViewFactory {

    fun getCovidResultViewData(): CovidResultViewData {
        return CovidResultViewData(0, 0, getCurrentDateFormatApi())
    }

    fun getDateForCallApi(): String {
        return getCurrentDateFormatApi()
    }
}