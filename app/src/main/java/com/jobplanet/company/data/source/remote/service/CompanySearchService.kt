package com.jobplanet.company.data.source.remote.service

import com.jobplanet.company.domain.model.SearchResult
import com.jobplanet.company.util.Resource
import retrofit2.Response
import retrofit2.http.GET

interface CompanySearchService {
    @GET("mobile-config/test_data.json")
    suspend fun getCompanyList() : Response<SearchResult>
}