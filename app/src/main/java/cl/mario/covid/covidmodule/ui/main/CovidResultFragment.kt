package cl.mario.covid.covidmodule.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cl.mario.covid.covidmodule.R
import cl.mario.covid.covidmodule.databinding.FragmentCovidResultBinding
import cl.mario.covid.covidmodule.eventnavigation.EventObserver
import cl.mario.covid.covidmodule.ui.events.CovidEvents
import cl.mario.covid.covidmodule.ui.events.CovidEvents.OpenDialogClickAction
import cl.mario.covid.covidmodule.ui.events.CovidEvents.SeeResults
import cl.mario.covid.covidmodule.ui.viewdata.CovidResultViewData
import cl.mario.covid.covidmodule.util.CalendarManager
import cl.mario.covid.covidmodule.util.State
import cl.mario.covid.covidmodule.util.dateToStringApi

class CovidResultFragment() : Fragment() {

    private lateinit var binding: FragmentCovidResultBinding

    private lateinit var viewModel: CovidViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()
        viewModel.getCovidResults()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCovidResultBinding.inflate(inflater, container, false).apply {
            covidViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[CovidViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.eventsLiveData.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is OpenDialogClickAction -> {
                    openDatePickerDialog()
                }
                is SeeResults -> {
                    setData(it.data)
                }
                is CovidEvents.FetchCovidResults -> {
                    viewModel.getCovidResults(it.date)
                }
            }
        })

        viewModel.covidInfoStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is State.Error -> {
                    binding.errorView.loadError(it.message) {
                        viewModel.fetchCovidResults(viewModel.lastDateRequest)
                    }
                }
                is State.Success -> {
                    viewModel.seeResultsEvent(it.data)
                }
                else -> {
                    //do nothing
                }
            }
        }
    }

    private fun initListener() {
        binding.apply {
            chooseDateDialogBtn.setOnClickListener {
                viewModel.openDateDialog()
            }
        }
    }

    private fun openDatePickerDialog() {
        CalendarManager(context).apply {
            setOnSelectedListener { date ->
                viewModel.fetchCovidResults(dateToStringApi(date))
            }
            show()
        }
    }

    private fun setData(data: CovidResultViewData) {
        binding.apply {
            tvDate.text = getString(R.string.choose_date, data.date)
            tvConfirm.text = getString(R.string.confirmed_cases, data.confirmed)
            tvDeath.text = getString(R.string.deceased_people, data.death)
        }
    }
}