package com.jobplanet.company.domain.model

import androidx.annotation.LayoutRes
import com.google.gson.annotations.Expose
import com.jobplanet.company.R

data class Company(
    override val cell_type: String,
    val ranking:Int,
    val interview_difficulty:Float,
    val name:String,
    val salary_avg:Int,
    val web_site:String,
    val logo_path:String,
    val interview_question:String,
    val company_id:Int,
    val has_job_posting:String,
    val rate_total_avg:Float,
    val industry_id:Int,
    val review_summary:String,
    val type:String,
    val industry_name:String,
    val simple_desc:String,
):Items