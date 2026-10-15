package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.IndustryItem
import com.example.ui.components.IndustryCard
import com.example.ui.theme.GoldLight
import com.example.ui.theme.NavyPrimary
import com.example.util.Localization

@Composable
fun IndustriesScreen(
    industries: List<IndustryItem>,
    language: AppLanguage,
    onSelectIndustry: (String) -> Unit,
    onRequestConsultation: (service: String, industry: String) -> Unit
) {
    val isAr = language == AppLanguage.ARABIC

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("industries_screen_list"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NavyPrimary)
                    .padding(horizontal = 20.dp, vertical = 22.dp)
            ) {
                Text(
                    text = Localization.navIndustries(language),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isAr)
                        "استشارات قطاعية متخصصة ومواءمة تنظيمية تلبي اشتراطات الهيئات والوزارات السعودية"
                    else
                        "Tailored regulatory, workforce, and operational frameworks engineered for Saudi Arabia's priority sectors",
                    style = MaterialTheme.typography.bodySmall,
                    color = GoldLight,
                    lineHeight = 18.sp
                )
            }
        }

        items(industries) { industry ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                IndustryCard(
                    industry = industry,
                    language = language,
                    onLearnMore = { onSelectIndustry(industry.id) },
                    onRequestConsultation = { onRequestConsultation("Company Formation", industry.nameEn) }
                )
            }
        }
    }
}
