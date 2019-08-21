package com.ex.billablehours.timecard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ex.billablehours.core.mvvm.BaseFragment
import com.ex.billablehours.core.util.snackBar
import com.ex.billablehours.databinding.GroupedTimeCardFragmentBinding
import com.ex.billablehours.timecard.view.GroupedTimeCardView
import com.ex.billablehours.timecard.viewmodel.GroupedTimeCardViewModel
import com.ex.billablehours.timecard.viewmodel.factory.GroupedTimeCardFactory
import org.kodein.di.generic.instance

class GroupedTimeCardFragment : BaseFragment<GroupedTimeCardView>(), GroupedTimeCardView {

    override fun createView(): GroupedTimeCardView = this

    companion object {
        fun newInstance() = GroupedTimeCardFragment()
    }

    lateinit var binding: GroupedTimeCardFragmentBinding

    private val viewFactory: GroupedTimeCardFactory by instance()

    val viewModel: GroupedTimeCardViewModel by lazy {
        ViewModelProviders.of(this, viewFactory).get(GroupedTimeCardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GroupedTimeCardFragmentBinding.inflate(inflater)
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
