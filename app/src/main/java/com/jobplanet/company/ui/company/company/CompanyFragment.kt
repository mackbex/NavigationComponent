package com.jobplanet.company.ui.company.company

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.jobplanet.company.databinding.FragmentCompanyBinding
import com.jobplanet.company.domain.model.Company
import com.jobplanet.company.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyFragment : Fragment() {

    private var binding: FragmentCompanyBinding by autoCleared()
    private val detailViewModel: CompanyViewModel by viewModels()
    val args: CompanyFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompanyBinding.inflate(inflater, container, false)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            test.text = args.company.name

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}