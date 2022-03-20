package com.jobplanet.company.data.repository

import com.jobplanet.company.data.source.remote.service.CompanySearchService
import com.jobplanet.company.util.Resource
import com.jobplanet.company.domain.model.*
import com.jobplanet.company.domain.repository.CompanyRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.resume

class CompanyInfoRepositoryImpl @Inject constructor(
    private val companySearchService: CompanySearchService
): CompanyRepository {

    /**
     * 서버로부터 기업정보 리스트 받아옴
     */
    override suspend fun getCompanyList() = suspendCancellableCoroutine<Resource<SearchResult>> { co ->
        CoroutineScope(Dispatchers.IO).launch {
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