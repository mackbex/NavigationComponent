package com.jobplanet.companies.ui.company.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jobplanet.companies.databinding.FragmentCompaniesSearchBinding
import com.jobplanet.companies.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding by autoCleared<FragmentCompaniesSearchBinding>()

    private val searchViewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompaniesSearchBinding.inflate(inflater, container, false)
        return binding.root
    }



}