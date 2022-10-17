package cl.mario.covid.covidmodule.util

import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateExtensionsTest {

    @Test
    fun `when get the current day format for calling the api minus one day then get the current date from the method`(){
        //Given
        val today = LocalDate.now().minusDays(1)
        //When
        //Then
        assert(getCurrentDateFormatApi() == today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    }

    @Test
    fun `when get the current date with spanish format`(){
        //Given
        val today = LocalDate.now().minusDays(1)
        //When
        val formatDate = getCurrentDateWithSpanishFormat(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        //Then
        assert(getCurrentDateWithSpanishFormat() == formatDate)

    }
}