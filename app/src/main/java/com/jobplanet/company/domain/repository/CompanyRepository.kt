package com.jobplanet.company.domain.repository

import com.jobplanet.company.data.source.remote.Resource
import com.jobplanet.company.domain.model.SearchResult

interface CompanyRepository {
    suspend fun getCompanyList() : Resource<SearchResult>
}