package com.jobplanet.company.ui.company.detail

import androidx.fragment.app.viewModels
import com.jobplanet.company.databinding.FragmentCompanyDetailBinding
import com.jobplanet.company.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentCompanyDetailBinding>(
    FragmentCompanyDetailBinding::inflate
){
    private val detailViewModel: DetailViewModel by viewModels()

    override fun initStartView() {
        binding.test.text = "Detail"
    }

    override fun initObservers() {

    }

    override fun initAfterBinding() {

    }
}