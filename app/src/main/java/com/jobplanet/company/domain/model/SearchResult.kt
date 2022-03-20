package com.jobplanet.company.domain.model

data class SearchResult(
    val minimum_interviews:Int,
    val total_page:Int,
    val minimum_reviews:Int,
    val total_count:Int,
    val items:List<Items>,
    val page:Int,
    val page_size:Int,
    val minimum_salaries:Int
)

interface Items {
    val cell_type:String
    val layout_id:Int
}