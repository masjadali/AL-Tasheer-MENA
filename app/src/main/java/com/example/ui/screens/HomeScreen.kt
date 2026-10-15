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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.data.model.FaqItem
import com.example.data.model.IndustryItem
import com.example.data.model.ServiceItem
import com.example.data.model.TestimonialItem
import com.example.data.model.TimelineStep
import com.example.ui.components.FaqAccordion
import com.example.ui.components.IndustryCard
import com.example.ui.components.ServiceCard
import com.example.ui.components.StepperTimeline
import com.example.ui.components.TestimonialCard
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.NavyVariant
import com.example.ui.theme.SaudiGreen
import com.example.ui.theme.SlateLightBorder
import com.example.util.Localization

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    services: List<ServiceItem>,
    industries: List<IndustryItem>,
    timelineSteps: List<TimelineStep>,
    testimonials: List<TestimonialItem>,
    trustedClients: List<String>,
    faqs: List<FaqItem>,
    language: AppLanguage,
    onNavigateToServices: () -> Unit,
    onNavigateToIndustries: () -> Unit,
    onSelectService: (String) -> Unit,
    onSelectIndustry: (String) -> Unit,
    onRequestConsultation: (service: String, industry: String) -> Unit
) {
    val isAr = language == AppLanguage.ARABIC

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen_list"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // 1. HERO SECTION
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_section")
            ) {
                // Background Hero Image with Executive Overlay
                Box(modifier = Modifier.fillMaxWidth().height(360.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.img_hero_saudi),
                        contentDescription = "Riyadh & Jeddah Corporate Skyline",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        NavyPrimary.copy(alpha = 0.75f),
                                        NavyPrimary.copy(alpha = 0.95f)
                                    )
                                )
                            )
                    )
                }

                // Hero Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(GoldPrimary.copy(alpha = 0.2f))
                            .border(1.dp, GoldPrimary, RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (isAr) "شريك الأعمال الأول في المملكة" else "Kingdom of Saudi Arabia • Corporate Gateway",
                            style = MaterialTheme.typography.labelSmall,
                            color = GoldLight,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = Localization.heroHeadline(language),
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 34.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = Localization.heroSupporting(language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.88f),
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Hero CTAs
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { onRequestConsultation("Company Formation & Related Services", "Technology") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = NavyPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1.3f)
                                .height(48.dp)
                                .testTag("hero_primary_cta")
                        ) {
                            Text(
                                text = Localization.getConsultation(language),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        OutlinedButton(
                            onClick = onNavigateToServices,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("hero_secondary_cta")
                        ) {
                            Text(
                                text = Localization.exploreServices(language),
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Quick Capability Highlights
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val highlights = if (isAr) listOf(
                            "⚡ تملك أجنبي 100% وترخيص MISA",
                            "👥 تأشيرات وتعهيد قوى عاملة",
                            "🏛️ امتثال زاتكا وقوى ومقيم",
                            "🚢 تخليص جمركي بموانئ المملكة"
                        ) else listOf(
                            "⚡ 100% Foreign Ownership (MISA)",
                            "👥 Manpower & Block Visas",
                            "🏛️ ZATCA, Qiwa & Muqeem",
                            "🚢 Seaport Customs Clearance"
                        )
                        highlights.forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color.White.copy(alpha = 0.15f))
                                    .border(1.dp, GoldLight.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = tag,
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }

        // 2. KEY STATS BANNER
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(number = "1984", label = if (isAr) "سنة التأسيس" else "Founded")
                        StatDivider()
                        StatItem(number = "500+", label = if (isAr) "مشروع مُسلَّم" else "Projects")
                        StatDivider()
                        StatItem(number = "98%", label = if (isAr) "احتفاظ بالعملاء" else "Retention")
                        StatDivider()
                        StatItem(number = "15+", label = if (isAr) "سنوات خبرة" else "Yrs Expertise")
                    }
                }
            }
        }

        // 3. OUR BUSINESS DIVISIONS
        item {
            Spacer(modifier = Modifier.height(28.dp))
            SectionHeader(
                title = Localization.businessDivisionsTitle(language),
                subtitle = if (isAr) "حلول تشغيلية وقانونية شاملة لكافة مراحل نمو شركتك بالمملكة" else "Turnkey corporate solutions across all 8 strategic divisions",
                actionText = if (isAr) "عرض كافة الخدمات (8)" else "View All (8)",
                onActionClick = onNavigateToServices
            )
        }

        items(services) { service ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                ServiceCard(
                    service = service,
                    language = language,
                    onLearnMore = { onSelectService(service.id) },
                    onRequestConsultation = { onRequestConsultation(service.titleEn, "Technology") }
                )
            }
        }

        // 4. WHY CHOOSE AL TASHEER MENA
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    text = Localization.whyChooseUsTitle(language),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (isAr)
                        "الريادة في حلول الاستثمار وإدارة العمليات المؤسسية في المملكة"
                    else
                        "Decades of on-the-ground regulatory expertise, operational speed, and Vision 2030 alignment.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )

                WhyChooseCard(
                    icon = Icons.Default.Shield,
                    title = if (isAr) "اعتماد وتصريح حكومي 100%" else "100% Regulatory Compliance",
                    desc = if (isAr)
                        "إتقان تام لكافة منصات وقوانين المملكة (MISA، قوى، مقيم، زاتكا، بلدي) لضمان بيئة عمل خالية من المخالفات."
                    else
                        "Complete mastery of MISA, Qiwa, Muqeem, ZATCA, and Balady rules to protect your enterprise."
                )

                Spacer(modifier = Modifier.height(10.dp))

                WhyChooseCard(
                    icon = Icons.Default.Policy,
                    title = if (isAr) "شريك متكامل من التأسيس إلى التوسع" else "End-to-End Capabilities",
                    desc = if (isAr)
                        "نوفر التأسيس القانوني، والكوادر البشرية، والتمويل، واللوجستيات، والإنشاءات تحت مظلة موحدة."
                    else
                        "From incorporation and manpower to logistics and commercial contracting under one trusted roof."
                )

                Spacer(modifier = Modifier.height(10.dp))

                WhyChooseCard(
                    icon = Icons.Default.TrendingUp,
                    title = if (isAr) "مواكبة رؤية السعودية 2030" else "Vision 2030 Strategic Alignment",
                    desc = if (isAr)
                        "استشارات استباقية تمكنك من الاستفادة من مبادرات التحول الوطني والمشاريع الكبرى وحوافز الاستثمار."
                    else
                        "Positioning our partners to capture national transformation initiatives and giga-project supply chains."
                )
            }
        }

        // 5. HOW IT WORKS (6-Step Stepper Timeline)
        item {
            Spacer(modifier = Modifier.height(28.dp))
            SectionHeader(
                title = Localization.howItWorksTitle(language),
                subtitle = if (isAr) "مسار منظم من 6 خطوات يضمن نجاح دخولك ونموك في السوق السعودي" else "A proven 6-step roadmap engineered for speed and compliance"
            )
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                StepperTimeline(steps = timelineSteps, language = language)
            }
        }

        // 6. INDUSTRIES WE SERVE
        item {
            Spacer(modifier = Modifier.height(28.dp))
            SectionHeader(
                title = Localization.industriesTitle(language),
                subtitle = if (isAr) "خبرات مخصصة لأهم القطاعات الاستراتيجية في المملكة" else "Specialized market advisory across high-growth Saudi industries",
                actionText = if (isAr) "عرض الجميع" else "View All",
                onActionClick = onNavigateToIndustries
            )
        }

        items(industries.take(4)) { industry ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                IndustryCard(
                    industry = industry,
                    language = language,
                    onLearnMore = { onSelectIndustry(industry.id) },
                    onRequestConsultation = { onRequestConsultation("Company Formation", industry.nameEn) }
                )
            }
        }

        // 7. TRUSTED BY LEADERS & REGISTERED WITH
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NavyPrimary)
                    .padding(vertical = 24.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = Localization.trustedLeadersTitle(language),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = GoldLight
                )
                Text(
                    text = if (isAr)
                        "منظمات وشركات قيادية نعتز بالتعاون معها وتقديم الخدمات المؤسسية لها"
                    else
                        "Prestigious corporations and organizations collaborating with Al Tasheer Mena",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )

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

        // 8. TESTIMONIALS
        item {
            Spacer(modifier = Modifier.height(28.dp))
            SectionHeader(
                title = Localization.testimonialsTitle(language),
                subtitle = if (isAr) "تجارب حقيقية لشركائنا في تأسيس وتشغيل أعمالهم" else "Endorsements from corporate partners and executive leaders"
            )
        }

        items(testimonials) { testimonial ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                TestimonialCard(testimonial = testimonial, language = language)
            }
        }

        // 9. FAQ PREVIEW
        item {
            Spacer(modifier = Modifier.height(28.dp))
            SectionHeader(
                title = Localization.faqTitle(language),
                subtitle = if (isAr) "إجابات الخبراء عن الأسئلة الشائعة حول كافة قطاعات الأعمال واللوائح" else "Official answers to key corporate, licensing, and compliance questions"
            )
        }

        items(faqs) { faq ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                FaqAccordion(faq = faq, language = language)
            }
        }

        // 10. FINAL CORPORATE CTA BANNER
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = NavyVariant),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.5f))
                ) {
                    Column(
                        modifier = Modifier.padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isAr) "هل أنت جاهز لتوسيع أعمالك في المملكة؟" else "Ready to Establish & Scale in Saudi Arabia?",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isAr)
                                "تواصل اليوم مع مستشاري التأشير مينا للحصول على خارطة طريق متكاملة لتأسيس وترخيص مشروعك."
                            else
                                "Connect with our advisory partners today for an actionable market entry and expansion roadmap.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Button(
                            onClick = { onRequestConsultation("Company Formation", "Technology") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = NavyPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = Localization.getConsultation(language),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            if (actionText != null && onActionClick != null) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelMedium,
                    color = GoldDark,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onActionClick() }
                        .padding(start = 8.dp)
                )
            }
        }
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = NavyPrimary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = GoldDark,
            fontSize = 11.sp
        )
    }
}

@Composable
fun StatDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(34.dp)
            .background(SlateLightBorder)
    )
}

@Composable
fun WhyChooseCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    desc: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(NavyPrimary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = GoldLight,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
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
