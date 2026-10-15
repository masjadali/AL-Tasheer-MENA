package com.example.data.model

data class IndustryItem(
    val id: String,
    val nameEn: String,
    val nameAr: String,
    val descriptionEn: String,
    val descriptionAr: String,
    val relevantServicesEn: List<String>,
    val relevantServicesAr: List<String>,
    val marketInsightsEn: String,
    val marketInsightsAr: String,
    val iconCategory: String
)
