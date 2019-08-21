package com.ex.billablehours.timecard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ex.billablehours.core.mvvm.BaseFragment
import com.ex.billablehours.core.util.snackBar
import com.ex.billablehours.databinding.TimeCardListFragmentBinding
import com.ex.billablehours.timecard.view.TimeCardView
import com.ex.billablehours.timecard.viewmodel.TimeCardListViewModel
import com.ex.billablehours.timecard.viewmodel.factory.TimeCardListFactory
import org.kodein.di.generic.instance

class TimeCardListFragment : BaseFragment<TimeCardView>(), TimeCardView {

    override fun createView(): TimeCardView = this

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
    }

    override fun showError(error: String?) {
        error?.let { view?.snackBar(error) }
    }
}
