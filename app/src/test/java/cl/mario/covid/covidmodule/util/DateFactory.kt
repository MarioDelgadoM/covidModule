package cl.mario.covid.covidmodule.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateFactory {

    fun getDateToApi(): String {
        val today = LocalDate.now().minusDays(1)
        return today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}