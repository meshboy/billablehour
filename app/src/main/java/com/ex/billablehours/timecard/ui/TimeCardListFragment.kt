package com.ex.billablehours.timecard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import com.ex.billablehours.core.data.timecard.entities.asDoaminModel
import com.ex.billablehours.core.mvvm.BaseFragment
import com.ex.billablehours.core.util.hide
import com.ex.billablehours.core.util.show
import com.ex.billablehours.core.util.snackBar
import com.ex.billablehours.databinding.TimeCardListFragmentBinding
import com.ex.billablehours.timecard.adapter.TimeCardListAdapter
import com.ex.billablehours.timecard.view.TimeCardListView
import com.ex.billablehours.timecard.viewmodel.TimeCardListViewModel
import com.ex.billablehours.timecard.viewmodel.factory.TimeCardListFactory
import org.kodein.di.generic.instance
import timber.log.Timber

class TimeCardListFragment : BaseFragment<TimeCardListView>(), TimeCardListView {

    override fun createView(): TimeCardListView = this

    companion object {
        fun newInstance() = TimeCardListFragment()
    }

    lateinit var binding: TimeCardListFragmentBinding

    private val viewFactory: TimeCardListFactory by instance()

    val viewModel: TimeCardListViewModel by lazy {
        ViewModelProviders.of(this, viewFactory).get(TimeCardListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TimeCardListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.view = createView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        val adapter = TimeCardListAdapter()
        binding.timeCardRecyclerView.adapter = adapter

//        on item clicked, navigate to time card creation screen
        adapter.setListener(TimeCardListAdapter.OnClickListener { model ->
            viewModel.navigateToTimeCardCreationScreen(model)
        })

        adapter.setLongListener(TimeCardListAdapter.OnLongClickListener { model ->
            Timber.d("mesh %s", model)
        })

        val projectName = TimeCardListFragmentArgs.fromBundle(arguments!!).projectName

        viewModel.timeCardRepository.getTimeCardsByProjectName(projectName).observe(this, Observer {
            if (it.isNotEmpty()) {
                binding.emptyTimeCard.hide(isGone = true)
                adapter.submitList(it.asDoaminModel())
            } else {
                binding.emptyTimeCard.show()
            }
        })
    }

    override fun navigateToTimeCreationScreen(timeCardModel: TimeCardModel?) {
        findNavController().navigate(
            TimeCardListFragmentDirections.actionTimeCardListFragmentToTimeCardFragment(
                timeCardModel
            )
        )
    }

    override fun showError(error: String?) {
        error?.let { view?.snackBar(error) }
    }
}
