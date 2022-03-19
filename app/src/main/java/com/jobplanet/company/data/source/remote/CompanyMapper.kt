package com.jobplanet.company.data.source.remote

import com.jobplanet.company.domain.model.Company
import com.jobplanet.company.util.EntityMapper
import javax.inject.Inject

//class CompanyMapper @Inject constructor() : EntityMapper<, Company> {
//
//    override fun mapFromEntity(entity: BlogObjectResponse): Company {
//        return Company(
//            id = entity.id,
//            title = entity.title,
//            body = entity.body,
//            image = entity.image,
//            category = entity.category
//        )
//    }
//
//    override fun mapToEntity(model: Company): BlogObjectResponse {
//        return BlogObjectResponse(
//            id = model.id,
//            title = model.title,
//            body = model.body,
//            image = model.image,
//            category = model.category
//        )
//    }
//
//    fun mapFromEntityList(entities:List<BlogObjectResponse>):List<Company>{
//        return entities.map { mapFromEntity(it) }
//    }
//}