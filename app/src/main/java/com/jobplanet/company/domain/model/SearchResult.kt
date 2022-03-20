package com.jobplanet.company.domain.model

data class SearchResult(
    val minimum_interviews:Int = 0,
    val total_page:Int = 0,
    val minimum_reviews:Int = 0,
    val total_count:Int = 0,
    val items:List<Items> = listOf(),
    val page:Int = 0,
    val page_size:Int = 0,
    val minimum_salaries:Int = 0
)

interface Items {
    val cell_type:String
    val layout_id:Int
}