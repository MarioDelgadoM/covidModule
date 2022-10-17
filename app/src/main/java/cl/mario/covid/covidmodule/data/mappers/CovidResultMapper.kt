package cl.mario.covid.covidmodule.data.mappers

import cl.mario.covid.covidmodule.data.models.CovidResultsData
import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData
import cl.mario.covid.covidmodule.util.getCurrentDateWithSpanishFormat
import cl.mario.covid.covidmodule.util.orElse
import javax.inject.Inject

class CovidResultMapper @Inject constructor() {
    fun executeMapping(data: CovidResultsData?) =
        if (data == null) {
            CovidResultViewData()
        } else {
            CovidResultViewData(
                data.info?.confirmed.orElse(0),
                data.info?.deaths.orElse(0),
                getCurrentDateWithSpanishFormat(
                    data.info?.date.orElse(
                        getCurrentDateWithSpanishFormat()
                    )
                )
            )
        }
}
