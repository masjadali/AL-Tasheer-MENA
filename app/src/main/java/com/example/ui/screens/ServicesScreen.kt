package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.ServiceItem
import com.example.ui.components.ServiceCard
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.NavyPrimary
import com.example.util.Localization

@Composable
fun ServicesScreen(
    services: List<ServiceItem>,
    language: AppLanguage,
    onSelectService: (String) -> Unit,
    onRequestConsultation: (service: String, industry: String) -> Unit
) {
    val isAr = language == AppLanguage.ARABIC
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf("All") }

    val filterOptions = listOf(
        "All" to if (isAr) "كافة الخدمات" else "All Services",
        "formation" to if (isAr) "التأسيس والترخيص" else "Formation & MISA",
        "manpower" to if (isAr) "القوى العاملة" else "Manpower",
        "compliance" to if (isAr) "الامتثال الحكومي" else "Compliance & ZATCA",
        "finance" to if (isAr) "الاستشارات المالية" else "Financial Advisory",
        "logistics" to if (isAr) "اللوجستيات" else "Logistics & Ports",
        "construction" to if (isAr) "المقاولات" else "Construction",
        "landscaping" to if (isAr) "اللاندسكيب" else "Landscaping",
        "food" to if (isAr) "الأغذية" else "Food & SFDA"
    )

    val filteredServices = services.filter { service ->
        val matchesCategory = if (selectedCategoryFilter == "All") true else service.iconCategory == selectedCategoryFilter
        val matchesSearch = if (searchQuery.isBlank()) true else {
            service.titleEn.contains(searchQuery, ignoreCase = true) ||
            service.titleAr.contains(searchQuery, ignoreCase = true) ||
            service.descriptionEn.contains(searchQuery, ignoreCase = true) ||
            service.descriptionAr.contains(searchQuery, ignoreCase = true)
        }
        matchesCategory && matchesSearch
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("services_screen_list"),
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
                    text = Localization.navServices(language),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isAr)
                        "8 قطاعات أعمال متخصصة تقود مسيرة شركتك نحو النجاح والامتثال الكامل في السعودية"
                    else
                        "8 specialized corporate divisions engineered to accelerate your Saudi market entry and operational growth",
                    style = MaterialTheme.typography.bodySmall,
                    color = GoldLight,
                    lineHeight = 18.sp
                )
            }
        }

        // Search & Category Chips
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            text = if (isAr) "ابحث عن خدمة أو قطاع..." else "Search services, MISA, logistics...",
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = GoldDark
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("services_search_input")
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filterOptions) { (key, label) ->
                        val isSelected = selectedCategoryFilter == key
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategoryFilter = key },
                            label = { Text(label, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NavyPrimary,
                                selectedLabelColor = GoldLight
                            )
                        )
                    }
                }
            }
        }

        // Service List
        items(filteredServices) { service ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                ServiceCard(
                    service = service,
                    language = language,
                    onLearnMore = { onSelectService(service.id) },
                    onRequestConsultation = { onRequestConsultation(service.titleEn, "Technology") }
                )
            }
        }
    }
}
