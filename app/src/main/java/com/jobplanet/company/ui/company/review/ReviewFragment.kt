package com.jobplanet.company.ui.company.review

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.jobplanet.company.databinding.FragmentReviewBinding
import com.jobplanet.company.domain.model.Review
import com.jobplanet.company.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : Fragment() {

    private var binding: FragmentReviewBinding by autoCleared()
    private val viewModel: ReviewViewModel by viewModels()
    val args: ReviewFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            test.text = args.review.name

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }


        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}