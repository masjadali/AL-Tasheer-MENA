package com.example.data.model

data class FaqItem(
    val id: String,
    val category: String,
    val questionEn: String,
    val questionAr: String,
    val answerEn: String,
    val answerAr: String
)

data class TestimonialItem(
    val id: String,
    val clientName: String,
    val clientRoleEn: String,
    val clientRoleAr: String,
    val company: String,
    val quoteEn: String,
    val quoteAr: String
)

data class TimelineStep(
    val stepNumber: Int,
    val titleEn: String,
    val titleAr: String,
    val descriptionEn: String,
    val descriptionAr: String,
    val highlightEn: String,
    val highlightAr: String
)

enum class AppLanguage(val code: String, val displayName: String, val isRtl: Boolean) {
    ENGLISH("en", "English", false),
    ARABIC("ar", "العربية", true)
}
