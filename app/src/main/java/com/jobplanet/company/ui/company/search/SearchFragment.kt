package com.jobplanet.company.ui.company.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.jobplanet.company.R
import com.jobplanet.company.data.source.remote.Resource
import com.jobplanet.company.databinding.FragmentCompanySearchBinding
import com.jobplanet.company.domain.model.Company
import com.jobplanet.company.domain.model.Review
import com.jobplanet.company.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding : FragmentCompanySearchBinding by autoCleared()
    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanySearchBinding.inflate(inflater, container, false)
        with(binding) {
            viewModel = this@SearchFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            rcSearchResult.adapter = SearchResultAdapter().apply {
                setOnItemClickListener { item ->
                    val direction = when (item) {
                        is Company -> {
                            SearchFragmentDirections.actionSearchFragmentToCompanyFragment(item)
                        }
                        is Review -> {
                            SearchFragmentDirections.actionSearchFragmentToReviewFragment(item)
                        }
                        else -> null
                    }
                    direction?.run {
                        findNavController().navigate(direction)
                    }
                }
            }

            val searchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
            searchView.queryHint = getString(R.string.hint_input_company)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("test","$newText")
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
            })
        }

        initStates()
        setHasOptionsMenu(true)

        viewModel.getSearchResult()
        return binding.root
    }


    private fun initStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.companyListState.collectLatest {
                        val item = binding.toolbar.menu.findItem(R.id.action_search)
                        when(it) {
                            is Resource.Loading -> {
                                binding.shimmerLayout.startShimmer()
                                item.isVisible = false
                            }
                            is Resource.Success -> {
                                binding.shimmerLayout.stopShimmer()
                                item.isVisible = true
                            }
                            is Resource.Failure -> {
                                binding.shimmerLayout.stopShimmer()
                                item.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }
}