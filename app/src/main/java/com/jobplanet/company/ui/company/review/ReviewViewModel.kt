package com.jobplanet.company.ui.company.review

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(

): ViewModel(){

    val moreReviewInfoState = MutableStateFlow<String>("")

    fun onMoreReviewInfoClick(companyName:String) {
        moreReviewInfoState.value = ""
        moreReviewInfoState.value = companyName
    }
}