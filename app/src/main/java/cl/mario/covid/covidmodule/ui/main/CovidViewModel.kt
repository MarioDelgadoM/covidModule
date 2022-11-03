package cl.mario.covid.covidmodule.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.mario.covid.covidmodule.data.useCases.GetDataCovidUseCase
import cl.mario.covid.covidmodule.eventnavigation.RouterEvent
import cl.mario.covid.covidmodule.ui.events.CovidEvents
import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData
import cl.mario.covid.covidmodule.util.DefaultViewModel
import cl.mario.covid.covidmodule.util.State
import cl.mario.covid.covidmodule.util.getCurrentDateFormatApi
import cl.mario.covid.covidmodule.util.toLiveData
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

    private val _eventsLiveData: MutableLiveData<RouterEvent<CovidEvents>> = MutableLiveData()
    val eventsLiveData = _eventsLiveData.toLiveData()


    fun getCovidResults(date: String = getCurrentDateFormatApi()) =
        handleView(getDataCovidUseCase.execute(date), _covidInfoStateLiveData,
            onLoading = {
                lastDateRequest = date
            },
            onSuccess = {
                _covidDataResultLiveData.postValue(it)
            })

    fun openDateDialog() {
        _eventsLiveData.postValue(RouterEvent(CovidEvents.OpenDialogClickAction))
    }

}

