package com.jobplanet.company.data.repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.jobplanet.company.data.source.remote.service.CompanySearchService
import com.jobplanet.company.util.Resource
import com.jobplanet.company.domain.model.*
import com.jobplanet.company.domain.repository.CompanyRepository
import com.jobplanet.company.util.RuntimeTypeAdapterFactory
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.resume

class CompanyInfoRepositoryImpl @Inject constructor(
    private val companySearchService: CompanySearchService
): CompanyRepository {

    override suspend fun getCompanyList() = suspendCancellableCoroutine<Resource<SearchResult>> { co ->
        CoroutineScope(co.context).launch {
            delay(10000)
            val response = companySearchService.getCompanyList()
            val result = if(response.isSuccessful) {
                val list = response.body()
                Resource.Success<SearchResult>(list ?: run {SearchResult()})
            }
            else {
                Resource.Failure(response.message())
            }
            co.resume(result)
        }
    }
}