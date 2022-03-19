package com.jobplanet.company.ui.company.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialContainerTransform
import com.jobplanet.company.R
import com.jobplanet.company.databinding.FragmentCompanyDetailBinding
import com.jobplanet.company.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var binding: FragmentCompanyDetailBinding by autoCleared()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompanyDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this



        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}