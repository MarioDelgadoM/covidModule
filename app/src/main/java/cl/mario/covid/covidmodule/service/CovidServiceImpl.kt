package cl.mario.covid.covidmodule.service

import androidx.fragment.app.Fragment
import cl.mario.covid.covidmodule.ui.main.CovidResultFragment

class CovidServiceImpl : ICovidService {
    override fun getCovidResultsFragment(): Fragment {
        return CovidResultFragment()
    }
}