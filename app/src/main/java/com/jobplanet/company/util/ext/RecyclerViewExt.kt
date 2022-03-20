package com.jobplanet.company.util.ext

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.company.R
import com.jobplanet.company.util.Resource
import com.jobplanet.company.domain.model.SearchResult
import com.jobplanet.company.domain.model.Theme
import com.jobplanet.company.ui.company.horizontal_theme.HorizontalThemeAdapter
import com.jobplanet.company.ui.company.search.SearchResultAdapter


@BindingAdapter("items")
fun bindItems(recyclerView: RecyclerView, items: Resource<SearchResult>?) {
    val adapter = recyclerView.adapter as SearchResultAdapter
    when(items) {
        is Resource.Success -> {
            adapter.submitList(items.data.items)
        }
        is Resource.Failure -> {
            Toast.makeText(recyclerView.context, recyclerView.context.getString(R.string.err_failed_load_data), Toast.LENGTH_SHORT).show()
        }
    }
}

@BindingAdapter("theme_items")
fun bindThemeItems(recyclerView: RecyclerView, items: List<Theme>?) {
    val adapter = recyclerView.adapter as HorizontalThemeAdapter
    adapter.submitList(items)
}