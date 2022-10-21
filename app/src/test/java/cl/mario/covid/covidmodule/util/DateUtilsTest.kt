package cl.mario.covid.covidmodule.util

import org.junit.Test
import java.time.LocalDate
import java.util.*

class DateUtilsTest {

    @Test
    fun should_returnDateFormat_when_getDateForCallAPI() {
        //Given
        val calendar = Calendar.getInstance()
        calendar.set(2022, 2, 1)
        //When
        val dateConverted = dateToStringApi(calendar.time)
        val fakeDateToCompare = LocalDate.of(2022, 3, 1)
        //Then
        assert(dateConverted == fakeDateToCompare.toString())
    }
}