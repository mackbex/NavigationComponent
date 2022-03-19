package com.jobplanet.company.ui.company.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jobplanet.company.data.source.remote.Resource
import com.jobplanet.company.domain.model.SearchResult
import com.jobplanet.company.domain.usecase.CompanyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val companyUseCase: CompanyUseCase
) : ViewModel(){

    val companyListState = MutableStateFlow<Resource<SearchResult>>(Resource.Loading)


    fun getSearchResult() {
        viewModelScope.launch {
            companyListState.value = companyUseCase.getSearchResult()
        }
    }
}