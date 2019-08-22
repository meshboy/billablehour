package com.ex.billablehours.timecard.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
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

    var dateSelected: String? = null
    var startTimeSelected: String? = null
    var stopTimeSelected: String? = null

    companion object {
        fun newInstance() = TimeCardFragment()
    }

    lateinit var binding: TimeCardFragmentBinding

    private val viewFactory: TimeCardFactory by instance()

    val viewModel: TimeCardViewModel by lazy {
        ViewModelProviders.of(this, viewFactory).get(TimeCardViewModel::class.java)
    }

    //    check if time screen is about to be udpated
    var updatedTimeCardModel: TimeCardModel? = null

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

        arguments?.let {
            updatedTimeCardModel = TimeCardFragmentArgs.fromBundle(it).timeCard
        }

        updatedTimeCardModel?.let {
            setToolbarMenu()

            binding.createTimeCardButton.text = "UPDATE"
            binding.billerRate.setText("${it.rate}")
            binding.projectName.setText(it.project)

            binding.selectStopTimeTextView.text = it.endTime
            stopTimeSelected = it.endTime

            binding.selectStartTimeTextView.text = it.startTime
            startTimeSelected = it.startTime

            binding.selectDateTextView.text = it.date
            dateSelected = it.date
        }

        viewModel.user.observe(this, Observer {
            viewModel.userModel = it.asUserModel()
        })

        binding.selectDateTextView.setOnClickListener {
            val datePickerFragment = DatePickerFragment { date ->
                binding.selectDateTextView.text = date
                dateSelected = date

            }
            datePickerFragment.show(fragmentManager!!, "DATE_PICKER")
        }

        binding.selectStartTimeTextView.setOnClickListener {
            val timePickerFragment = TimePickerFragment { time ->
                binding.selectStartTimeTextView.text = time
                startTimeSelected = time
            }
            timePickerFragment.show(fragmentManager!!, "TIME_PICKER")
        }

        binding.selectStopTimeTextView.setOnClickListener {
            val timePickerFragment = TimePickerFragment { time ->
                binding.selectStopTimeTextView.text = time
                stopTimeSelected = time
            }
            timePickerFragment.show(fragmentManager!!, "TIME_PICKER")
        }

        binding.createTimeCardButton.setOnClickListener {
            viewModel.createTimeCard(
                id = updatedTimeCardModel?.id,
                employeeId = updatedTimeCardModel?.employeeId,
                projectName = binding.projectName.text.toString(),
                billerRate = binding.billerRate.text.toString(),
                dateSelected = dateSelected,
                startTime = startTimeSelected,
                stopTime = stopTimeSelected
            )
        }
    }

    /**
     * add delete menu to toolbar component when there is need to update timecard
     */
    fun setToolbarMenu() {
        setHasOptionsMenu(true)
        val toolbar: Toolbar = activity?.findViewById(R.id.toolbar) as Toolbar
        toolbar.inflateMenu(R.menu.delete)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.deleteItem -> {
                    updatedTimeCardModel?.let {
                        viewModel.delete(it.id!!, it.project)
                    }
                }
            }
            true
        }
    }

    override fun navigateToTimeCardListPage(projectName: String) {
        findNavController().navigate(TimeCardFragmentDirections.actionTimeCardFragmentToTimeCardListFragment(projectName))
    }

    override fun showError(error: String?) {
        error?.let { view?.snackBar(error) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        val toolbar: Toolbar = activity?.findViewById(R.id.toolbar) as Toolbar
        toolbar.menu.clear()
    }
}
