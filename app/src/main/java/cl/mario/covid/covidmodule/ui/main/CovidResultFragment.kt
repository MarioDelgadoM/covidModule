package cl.mario.covid.covidmodule.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import cl.mario.covid.covidmodule.R
import cl.mario.covid.covidmodule.databinding.FragmentCovidResultBinding
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
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCovidResultBinding?>(inflater,R.layout.fragment_covid_result,container,false).apply {
            viewModel = covidViewModel
        }
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[CovidViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.covidInfoStateLiveData.observe(viewLifecycleOwner) {
            if (it is State.Error) {
                binding.errorView?.loadError(it.message) {
                    viewModel.getCovidResults(viewModel.lastDateRequest)
                }
            }
        }
    }

    private fun initListener() {
        binding.apply {
            chooseDateDialogBtn.setOnClickListener {
                CalendarManager(context).apply {
                    setOnSelectedListener { date ->
                        viewModel.getCovidResults(dateToStringApi(date))
                    }
                    show()
                }
            }
        }
    }
}