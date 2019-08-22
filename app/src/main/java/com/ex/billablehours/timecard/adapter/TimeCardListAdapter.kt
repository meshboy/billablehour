package com.ex.billablehours.timecard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import com.ex.billablehours.databinding.TimeCardContentListingBinding

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-22
 */
class TimeCardListAdapter:
    ListAdapter<TimeCardModel, TimeCardListAdapter.TimeCardViewHolder>(DiffCallback), View.OnLongClickListener {
    override fun onLongClick(v: View?): Boolean {
        return true
    }

    lateinit var onClickListener: OnClickListener

    fun setListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeCardViewHolder {
        return TimeCardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TimeCardViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
        holder.setListener {
            onClickListener.onClick(model)
        }

        holder.cardLayout.setOnLongClickListener(this)
    }

    class TimeCardViewHolder(private val binding: TimeCardContentListingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val cardLayout = binding.timeCardLayout

        fun bind(timeCardModel: TimeCardModel) {
            binding.value = timeCardModel
            binding.executePendingBindings()
        }

        fun setListener(listener: (v: View) -> Unit) {
            binding.timeCardLayout.setOnClickListener(listener)
        }

        fun setLongListener(listener: (v: View) -> Unit) {

        }

        companion object {
            fun from(parent: ViewGroup): TimeCardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TimeCardContentListingBinding.inflate(layoutInflater, parent, false)
                return TimeCardViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TimeCardModel>() {
        override fun areItemsTheSame(oldItem: TimeCardModel, newItem: TimeCardModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TimeCardModel, newItem: TimeCardModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (model: TimeCardModel) -> Unit) {
        fun onClick(timeCardModel: TimeCardModel) = clickListener(timeCardModel)
    }

}