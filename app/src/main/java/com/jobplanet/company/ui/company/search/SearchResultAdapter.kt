package com.jobplanet.company.ui.company.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.company.R
import com.jobplanet.company.databinding.ItemCompanyBinding
import com.jobplanet.company.domain.model.*
import java.lang.IllegalStateException

class SearchResultAdapter : ListAdapter<Items, SearchResultAdapter.ViewHolder>(ItemDiffCallback()) {
    private var listener: ((item:Items) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(holder.bindingAdapterPosition), holder.itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].layout_id
    }

    fun setOnItemClickListener(listener: ((item:Items) -> Unit)?) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ViewDataBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Items, view: View) {
            binding.setVariable(BR.model, item)
                view.setOnClickListener {
                    listener?.invoke(item)
                }

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

