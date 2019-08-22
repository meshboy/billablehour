package com.ex.billablehours.timecard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ex.billablehours.R
import com.ex.billablehours.core.data.user.entities.asUserModel
import com.ex.billablehours.core.mvvm.BaseFragment
import com.ex.billablehours.core.util.snackBar
import com.ex.billablehours.databinding.TimeCardFragmentBinding
import com.ex.billablehours.timecard.view.TimeCardView
import com.ex.billablehours.timecard.viewmodel.TimeCardViewModel
import com.ex.billablehours.timecard.viewmodel.factory.TimeCardFactory
import org.kodein.di.generic.instance

class TimeCardFragment : BaseFragment<TimeCardView>(), TimeCardView {

    var startTime: String? = null
    var stopTime: String? = null
    var dateSelected: String? = null

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

        viewModel.user.observe(this, Observer {
            viewModel.userModel = it.asUserModel()
        })

        binding.selectDateTextView.setOnClickListener {
            val datePickerFragment = DatePickerFragment { date ->
                dateSelected = date
                binding.selectDateTextView.text = dateSelected
            }
            datePickerFragment.show(fragmentManager!!, "DATE_PICKER")
        }

        binding.selectStartTimeTextView.setOnClickListener {
            val timePickerFragment = TimePickerFragment { time ->
                startTime = time
                binding.selectStartTimeTextView.text = startTime
            }
            timePickerFragment.show(fragmentManager!!, "TIME_PICKER")
        }

        binding.selectStopTimeTextView.setOnClickListener {
            val timePickerFragment = TimePickerFragment { time ->
                stopTime = time
                binding.selectStopTimeTextView.text = stopTime
            }
            timePickerFragment.show(fragmentManager!!, "TIME_PICKER")
        }

        binding.createTimeCardButton.setOnClickListener {
            viewModel.createTimeCard(
                projectName = binding.projectName.text.toString(),
                billerRate = binding.billerRate.text.toString(),
                dateSelected = dateSelected,
                startTime = startTime,
                stopTime = stopTime
            )
        }
    }

    override fun navigateToTimeCardListPage() {
        findNavController().navigate(R.id.action_timeCardFragment_to_timeCardListFragment)
    }

    override fun showError(error: String?) {
        error?.let { view?.snackBar(error) }
    }
}
