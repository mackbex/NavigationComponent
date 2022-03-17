package com.jobplanet.company.ui.company.search

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jobplanet.company.R
import com.jobplanet.company.databinding.FragmentCompanySearchBinding
import com.jobplanet.company.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentCompanySearchBinding>(
    FragmentCompanySearchBinding::inflate
) {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun initStartView() {
        binding.search.text = "SEARCH523"
        binding.search.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment())
        }
    }
    override fun initObservers() {}
    override fun initAfterBinding() {}



}