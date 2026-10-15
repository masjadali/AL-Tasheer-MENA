package com.example.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ServiceItem(
    val id: String,
    val titleEn: String,
    val titleAr: String,
    val taglineEn: String,
    val taglineAr: String,
    val descriptionEn: String,
    val descriptionAr: String,
    val keyBenefitsEn: List<String>,
    val keyBenefitsAr: List<String>,
    val whatWeProvideEn: List<String>,
    val whatWeProvideAr: List<String>,
    val processEn: List<String>,
    val processAr: List<String>,
    val relevantIndustriesEn: List<String>,
    val relevantIndustriesAr: List<String>,
    val ctaEn: String,
    val ctaAr: String,
    val whatsappMessageEn: String,
    val whatsappMessageAr: String,
    val iconCategory: String
)
