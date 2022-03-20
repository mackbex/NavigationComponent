package com.jobplanet.company.ui.company.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.jobplanet.company.R
import com.jobplanet.company.util.Resource
import com.jobplanet.company.databinding.FragmentCompanySearchBinding
import com.jobplanet.company.databinding.ItemHorizontalThemeBinding
import com.jobplanet.company.domain.model.Company
import com.jobplanet.company.domain.model.HorizontalTheme
import com.jobplanet.company.domain.model.Review
import com.jobplanet.company.ui.company.horizontal_theme.HorizontalThemeAdapter
import com.jobplanet.company.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * 메인 Fragment
 */
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

                /**
                 * Item list 불러 온 후, 추가작업 (예 : 기업 상세 fragment로 이동 or Horizontal listview 어뎁터 설정 등) 담당
                 */
                setPostInterface { item, binding ->
                    when (item) {
                        is Company -> {
                            binding.root.setOnClickListener {
                                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToCompanyFragment(item))
                            }
                        }
                        is Review -> {
                            binding.root.setOnClickListener {
                                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToReviewFragment(item))
                            }
                        }
                        is HorizontalTheme -> {
                            (binding as ItemHorizontalThemeBinding).rcHorizontalTheme.adapter = HorizontalThemeAdapter()
                        }
                        else -> null
                    }
                }
            }
        }

        setSearchFeature()
        initStates()
        setHasOptionsMenu(true)

        viewModel.getSearchResult()
        return binding.root
    }

    /**
     * Appbar에 결과 내 재검색 기능 담당
     */
    private fun setSearchFeature() {
        val searchView = binding.toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = getString(R.string.hint_input_company)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                (binding.rcSearchResult.adapter as SearchResultAdapter).submitList(viewModel.filterCompanyList(newText))
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

    /**
     * State 구성
     */
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