package com.jobplanet.company.domain.model

data class Review(
    override val cell_type: String,
    val ranking:Int,
    val interview_difficulty:Float,
    val cons:String,
    val name:String,
    val days_ago:Int,
    val logo_path:String,
    val pros:String,
    val company_id:Int,
    val occupation_name:String,
    val rate_total_avg:Float,
    val industry_id:Int,
    val date:String,
    val review_summary:String,
    val type:String,
    val industry_name:String,
    val simple_desc:String,
):Items
