package com.ex.billablehours.timecard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ex.billablehours.core.data.timecard.domain.GroupedTimeCardModel
import com.ex.billablehours.databinding.GroupTimecardContentListingBinding

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
class GroupTimeCardAdapter :
    ListAdapter<GroupedTimeCardModel, GroupTimeCardAdapter.GroupTimeCardViewHolder>(DiffCallback) {

    lateinit var onClickListener: OnClickListener

    fun setListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupTimeCardViewHolder {
        return GroupTimeCardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GroupTimeCardViewHolder, position: Int) {
        val groupedTimeCardModel = getItem(position)
        holder.bind(groupedTimeCardModel)
        holder.setListener {
            onClickListener.onClick(groupedTimeCardModel.project)
        }
    }

    class GroupTimeCardViewHolder(private val binding: GroupTimecardContentListingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(groupedTimeCardModel: GroupedTimeCardModel) {
            binding.value = groupedTimeCardModel
            binding.executePendingBindings()
        }

        fun setListener(listener: (v: View) -> Unit) {
            binding.groupTimeCardView.setOnClickListener(listener)
        }

        companion object {
            fun from(parent: ViewGroup): GroupTimeCardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GroupTimecardContentListingBinding.inflate(layoutInflater, parent, false)
                return GroupTimeCardViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GroupedTimeCardModel>() {
        override fun areItemsTheSame(oldItem: GroupedTimeCardModel, newItem: GroupedTimeCardModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GroupedTimeCardModel, newItem: GroupedTimeCardModel): Boolean {
            return oldItem.project.toLowerCase() == newItem.project.toLowerCase()
        }
    }

    class OnClickListener(val clickListener: (projectName: String) -> Unit) {
        fun onClick(projectName: String) = clickListener(projectName)
    }
}