package cl.mario.covid.covidmodule.util

import org.junit.Test

class DateExtensionsTest {

    @Test
    fun should_getTheCurrentDateMinusOneDay_when_getTheCurrentDateFormatAPI() {
        assert(getCurrentDateFormatApi() == DateFactory().getDateToApi())
    }

    @Test
    fun should_getSpanishFormat_when_getTheCurrentDate() {
        val formatDate = getCurrentDateWithSpanishFormat(DateFactory().getDateToApi())

        assert(getCurrentDateWithSpanishFormat() == formatDate)

    }
}