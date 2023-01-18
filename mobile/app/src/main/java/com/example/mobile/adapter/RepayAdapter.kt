package com.example.mobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.databinding.RepayShowItemBinding
import com.example.mobile.model.MoneyItem

class RepayAdapter : ListAdapter<MoneyItem, RepayAdapter.RepayViewHolder>(DiffCallBack) {
    class RepayViewHolder(private var binding: RepayShowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moneyItem: MoneyItem) {
            binding.apply {
                item = moneyItem
            }
//            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepayViewHolder {
        val bind = RepayShowItemBinding.inflate(LayoutInflater.from(parent.context))
        return RepayViewHolder(bind)
    }

    override fun onBindViewHolder(holder: RepayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MoneyItem>() {
        override fun areItemsTheSame(oldItem: MoneyItem, newItem: MoneyItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MoneyItem, newItem: MoneyItem): Boolean {
            return oldItem == newItem
        }
    }
}