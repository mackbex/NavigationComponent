package com.jobplanet.company.ui.company.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jobplanet.company.R
import com.jobplanet.company.databinding.FragmentCompanySearchBinding
import com.jobplanet.company.ui.base.BaseFragment
import com.jobplanet.company.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding : FragmentCompanySearchBinding by autoCleared()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanySearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.text = "SEARCH523"
        binding.search.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment())
        }
    }
}