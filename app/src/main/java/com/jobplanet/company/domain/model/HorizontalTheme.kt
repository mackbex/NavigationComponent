package com.jobplanet.company.domain.model

import com.google.gson.annotations.Expose
import com.jobplanet.company.R

data class HorizontalTheme(
    override val cell_type: String,
    val count:Int,
    val themes:List<Theme>,
): Items
data class Theme(
    val color:String,
    val cover_image:String,
    val id:Int,
    val title:String
)