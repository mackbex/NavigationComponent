package com.jobplanet.company.ui.company.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.company.R
import com.jobplanet.company.domain.model.*
import java.lang.IllegalStateException

class HorizontalThemeAdapter : ListAdapter<Items, HorizontalThemeAdapter.ViewHolder>(ItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is Company -> R.layout.item_company
            is Review -> R.layout.item_review
            is HorizontalTheme -> R.layout.item_horizontal_theme
            else -> throw IllegalStateException("No registered layout.")
        }
    }

    inner class ViewHolder(private val binding: ViewDataBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Items) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }
    }


    private class ItemDiffCallback : DiffUtil.ItemCallback<Items>() {

        override fun areItemsTheSame(
            oldItem: Items,
            newItem: Items
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Items,
            newItem: Items
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}
