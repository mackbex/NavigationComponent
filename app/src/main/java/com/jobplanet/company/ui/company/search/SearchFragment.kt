package com.jobplanet.company.ui.company.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import com.jobplanet.company.BR
import com.jobplanet.company.R
import com.jobplanet.company.data.source.remote.Resource
import com.jobplanet.company.databinding.FragmentCompanySearchBinding
import com.jobplanet.company.ui.base.BaseFragment
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
            rcSearchResult.adapter = SearchResultAdapter()
        }

        initStates()
        viewModel.getSearchResult()
        return binding.root
    }

    private fun initStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.companyListState.collectLatest {
                        when(it) {
                            is Resource.Loading -> {

                            }
                            is Resource.Success -> {
                                it.data
                            }
                        }
                    }
                }
            }
        }
    }
}