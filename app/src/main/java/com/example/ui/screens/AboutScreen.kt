package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.AppLanguage
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.NavyVariant
import com.example.ui.theme.SlateLightBorder
import com.example.util.Localization

data class JourneyMilestone(
    val year: String,
    val titleEn: String,
    val titleAr: String,
    val descEn: String,
    val descAr: String
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutScreen(
    trustedClients: List<String>,
    language: AppLanguage,
    onOpenConsultation: () -> Unit,
    onOpenPrivacyPolicy: () -> Unit
) {
    val isAr = language == AppLanguage.ARABIC

    val milestones = listOf(
        JourneyMilestone(
            year = "1984",
            titleEn = "Founding & Strategic Establishment",
            titleAr = "التأسيس والانطلاقة الاستراتيجية",
            descEn = "Initiated corporate commercial services in the Western Province of Saudi Arabia, laying foundational roots in business and trade.",
            descAr = "انطلاق الخدمات التجارية والمؤسسية بالمنطقة الغربية في المملكة، وبناء الثقة مع رواد الأعمال."
        ),
        JourneyMilestone(
            year = "2005",
            titleEn = "Customs & Logistics Expansion",
            titleAr = "توسع قطاع اللوجستيات والتخليص",
            descEn = "Extended direct operational facilities into seaport customs brokerage at Jeddah Islamic Port and King Abdulaziz Port.",
            descAr = "توسيع العمليات لتشمل خدمات التخليص الجمركي بميناء جدة الإسلامي وميناء الملك عبدالعزيز."
        ),
        JourneyMilestone(
            year = "2016",
            titleEn = "Saudi Vision 2030 Modernization",
            titleAr = "مواكبة رؤية السعودية 2030",
            descEn = "Restructured corporate frameworks to pioneer 100% foreign investment licensing via MISA and advanced digital portal integrations.",
            descAr = "إعادة هيكلة الخدمات لدعم تراخيص الاستثمار الأجنبي المباشر (MISA) والتحول الرقمي الحكومي."
        ),
        JourneyMilestone(
            year = "2020",
            titleEn = "Turnkey Multi-Division Integration",
            titleAr = "التكامل متعدد القطاعات",
            descEn = "Established dedicated Food, Construction, Landscaping, and Manpower solutions to deliver comprehensive client support.",
            descAr = "إطلاق قطاعات الأغذية، والمقاولات، واللاندسكيب، والقوى العاملة لتقديم حلول شاملة."
        ),
        JourneyMilestone(
            year = "2026+",
            titleEn = "Vision Realization & Giga Projects",
            titleAr = "تحقيق المستهدفات والمشاريع الكبرى",
            descEn = "Serving national champions, foreign multinationals, and giga-project contractors across Riyadh, Jeddah, NEOM, and the Eastern Province.",
            descAr = "تمكين كبرى الشركات العالمية والمحلية في مشاريع الرياض وجدة ونيوم والمنطقة الشرقية."
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("about_screen_list"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // Hero Visual Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_about_corporate),
                    contentDescription = "Al Tasheer Mena Corporate Boardroom",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    NavyPrimary.copy(alpha = 0.65f),
                                    NavyPrimary.copy(alpha = 0.95f)
                                )
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(GoldPrimary.copy(alpha = 0.25f))
                            .border(1.dp, GoldPrimary, RoundedCornerShape(16.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (isAr) "تاريخ عريق • منذ 1984" else "Heritage & Leadership Since 1984",
                            style = MaterialTheme.typography.labelSmall,
                            color = GoldLight,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = Localization.aboutHeroTitle(language),
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        // Company Statistics Bar
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp, horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(number = "1984", label = if (isAr) "سنة التأسيس" else "Founded")
                        StatDivider()
                        StatItem(number = "500+", label = if (isAr) "مشروع ناجح" else "Projects")
                        StatDivider()
                        StatItem(number = "98%", label = if (isAr) "رضا الشركاء" else "Retention")
                        StatDivider()
                        StatItem(number = "15+", label = if (isAr) "خبرة قيادية" else "Yrs Expertise")
                    }
                }
            }
        }

        // Company Positioning & Narrative
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = if (isAr) "عن التأشير مينا" else "About Al Tasheer Mena",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = Localization.aboutOverview(language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 23.sp
                    )
                }
            }
        }

        // Our Journey Timeline
        item {
            Spacer(modifier = Modifier.height(28.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = if (isAr) "مسيرتنا وتطورنا (رحلة النجاح)" else "Our Journey & Evolution",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (isAr) "محطات رئيسية صنعت مكانتنا كشريك موثوق للأعمال في المملكة" else "Defining milestones shaping our identity as Saudi Arabia's premier business gateway",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp, bottom = 14.dp)
                )
            }
        }

        items(milestones) { milestone ->
            val title = if (isAr) milestone.titleAr else milestone.titleEn
            val desc = if (isAr) milestone.descAr else milestone.descEn

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(NavyPrimary)
                        .border(2.dp, GoldPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = milestone.year,
                        color = GoldLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Trusted By Leaders
        item {
            Spacer(modifier = Modifier.height(28.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NavyPrimary)
                    .padding(20.dp)
            ) {
                Text(
                    text = Localization.trustedLeadersTitle(language),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = GoldLight
                )
                Spacer(modifier = Modifier.height(14.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    trustedClients.forEach { client ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White.copy(alpha = 0.08f))
                                .border(1.dp, GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = client,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        // CTA & Privacy Policy link
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onOpenConsultation,
                    colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = Localization.getConsultation(language),
                        color = GoldLight,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onOpenPrivacyPolicy() }
                        .padding(8.dp)
                        .testTag("open_privacy_policy_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = GoldDark,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = Localization.privacyPolicyTitle(language),
                        style = MaterialTheme.typography.labelMedium,
                        color = GoldDark,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
