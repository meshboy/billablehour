package com.ex.billablehours.timecard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ex.billablehours.R
import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import com.ex.billablehours.core.data.user.entities.asUserModel
import com.ex.billablehours.core.mvvm.BaseFragment
import com.ex.billablehours.core.util.snackBar
import com.ex.billablehours.databinding.TimeCardFragmentBinding
import com.ex.billablehours.timecard.view.TimeCardView
import com.ex.billablehours.timecard.viewmodel.TimeCardViewModel
import com.ex.billablehours.timecard.viewmodel.factory.TimeCardFactory
import org.kodein.di.generic.instance

class TimeCardFragment : BaseFragment<TimeCardView>(), TimeCardView {

    override fun createView(): TimeCardView = this

    companion object {
        fun newInstance() = TimeCardFragment()
    }

    lateinit var binding: TimeCardFragmentBinding

    private val viewFactory: TimeCardFactory by instance()

    val viewModel: TimeCardViewModel by lazy {
        ViewModelProviders.of(this, viewFactory).get(TimeCardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TimeCardFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.view = createView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


//        check if time screen is about to be udpated
        var updatedTimeCardModel: TimeCardModel? = null

        arguments?.let {
            updatedTimeCardModel = TimeCardFragmentArgs.fromBundle(it).timeCard
        }

        updatedTimeCardModel?.let {
            binding.createTimeCardButton.text = "UPDATE"

            binding.selectStopTimeTextView.text = it.endTime
            binding.billerRate.setText("${it.rate}")
            binding.selectStartTimeTextView.text = it.startTime
            binding.projectName.setText(it.project)
            binding.selectDateTextView.text = it.date
        }

        viewModel.user.observe(this, Observer {
            viewModel.userModel = it.asUserModel()
        })

        binding.selectDateTextView.setOnClickListener {
            val datePickerFragment = DatePickerFragment { date ->
                binding.selectDateTextView.text = date
            }
            datePickerFragment.show(fragmentManager!!, "DATE_PICKER")
        }

        binding.selectStartTimeTextView.setOnClickListener {
            val timePickerFragment = TimePickerFragment { time ->
                binding.selectStartTimeTextView.text = time
            }
            timePickerFragment.show(fragmentManager!!, "TIME_PICKER")
        }

        binding.selectStopTimeTextView.setOnClickListener {
            val timePickerFragment = TimePickerFragment { time ->
                binding.selectStopTimeTextView.text = time
            }
            timePickerFragment.show(fragmentManager!!, "TIME_PICKER")
        }

        binding.createTimeCardButton.setOnClickListener {
            viewModel.createTimeCard(
                id = updatedTimeCardModel?.id,
                employeeId = updatedTimeCardModel?.employeeId,
                projectName = binding.projectName.text.toString(),
                billerRate = binding.billerRate.text.toString(),
                dateSelected = binding.selectDateTextView.text.toString(),
                startTime = binding.selectStartTimeTextView.text.toString(),
                stopTime = binding.selectStopTimeTextView.text.toString()
            )
        }
    }

    override fun navigateToTimeCardListPage(projectName: String) {
        findNavController().navigate(TimeCardFragmentDirections.actionTimeCardFragmentToTimeCardListFragment(projectName))
    }

    override fun showError(error: String?) {
        error?.let { view?.snackBar(error) }
    }
}
