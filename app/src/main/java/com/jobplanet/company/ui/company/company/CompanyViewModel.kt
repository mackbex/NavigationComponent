package com.jobplanet.company.ui.company.company

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(): ViewModel(){

    val moreCompanyInfoState = MutableStateFlow<String>("")

    fun onMoreCompanyInfoClick(companyName:String) {
        moreCompanyInfoState.value = ""
        moreCompanyInfoState.value = companyName
    }
}