package cl.mario.covid.covidmodule.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.mario.covid.covidmodule.data.useCases.GetDataCovidUseCase
import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData
import cl.mario.covid.covidmodule.util.DefaultViewModel
import cl.mario.covid.covidmodule.util.State
import cl.mario.covid.covidmodule.util.getCurrentDateFormatApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(private val getDataCovidUseCase: GetDataCovidUseCase) :
    DefaultViewModel() {

    var lastDateRequest: String = ""
        private set

    private val _covidInfoStateLiveData = MutableLiveData<State<CovidResultViewData>>()
    val covidInfoStateLiveData: LiveData<State<CovidResultViewData>> = _covidInfoStateLiveData

    private val _covidDataResultLiveData = MutableLiveData<CovidResultViewData>()
    val covidDataResultLiveData: LiveData<CovidResultViewData> = _covidDataResultLiveData

    fun getCovidResults(date: String = getCurrentDateFormatApi()) =
        handleView(getDataCovidUseCase.execute(date), _covidInfoStateLiveData,
            onLoading = {
                println("lastDateRequest: $lastDateRequest")
                        lastDateRequest = date
            },
            onSuccess = {
                println("onSuccess")
                _covidDataResultLiveData.postValue(it)
            })
}

