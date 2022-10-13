package cl.mario.covid.covidmodule.data.models

import com.google.gson.annotations.SerializedName

data class CovidResultsData(
    @SerializedName("data")
    val info: InfoData? = null
)