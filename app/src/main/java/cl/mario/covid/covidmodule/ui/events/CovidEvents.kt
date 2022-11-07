package cl.mario.covid.covidmodule.ui.events

import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData

sealed class CovidEvents {
    object OpenDialogClickAction : CovidEvents()
    data class SeeResults(val data: CovidResultViewData) : CovidEvents()
    data class FetchCovidResults(val date: String) : CovidEvents()
}