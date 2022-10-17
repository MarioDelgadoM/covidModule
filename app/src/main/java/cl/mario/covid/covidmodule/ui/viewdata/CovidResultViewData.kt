package cl.mario.covid.covidmodule.ui.viewdata

import cl.mario.covid.covidmodule.util.getCurrentDateWithSpanishFormat

data class CovidResultViewData(
    val confirmed: Int = 0,
    val death: Int = 0,
    val date: String = getCurrentDateWithSpanishFormat()
)