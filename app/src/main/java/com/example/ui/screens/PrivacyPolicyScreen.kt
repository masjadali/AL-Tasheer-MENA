package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.theme.GoldLight
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SlateLightBorder

@Composable
fun PrivacyPolicyScreen(
    language: AppLanguage,
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    val isAr = language == AppLanguage.ARABIC

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("privacy_policy_screen"),
        contentPadding = PaddingValues(bottom = 60.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NavyPrimary)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("privacy_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = GoldLight,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = if (isAr) "سياسة الخصوصية وحماية البيانات" else "Privacy & Data Protection Policy",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = if (isAr) "التزام الخصوصية لشركة التأشير مينا" else "Al Tasheer Mena Privacy Commitment",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isAr)
                            "تلتزم شركة التأشير مينا بحماية خصوصية عملائها وشركائها وفقاً لأحكام نظام حماية البيانات الشخصية (PDPL) في المملكة العربية السعودية ومعايير متجر Google Play."
                        else
                            "Al Tasheer Mena is committed to safeguarding the privacy and commercial confidentiality of corporate clients, investors, and users in accordance with Saudi Arabia's Personal Data Protection Law (PDPL) and Google Play Store policies.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = if (isAr) "1. البيانات التي نجمعها" else "1. Data We Collect",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isAr)
                            "نقوم بجمع المعلومات التي تقدمها طواعية عند إرسال طلب استشارة، وتشمل: الاسم، اسم المنشأة، البريد الإلكتروني، رقم الهاتف، والخدمة المطلوبة. لا يقوم التطبيق بالوصول إلى جهات الاتصال، أو الرسائل القصيرة SMS، أو سجل المكالمات، أو ملفات التخزين الخاصة."
                        else
                            "We collect only the information you voluntarily submit when requesting a consultation: full name, corporate entity, corporate email, telephone/WhatsApp contact number, and service requirements. This application does NOT request access to contacts, SMS, call logs, media storage, or camera.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = if (isAr) "2. استخدام المعلومات" else "2. How Data Is Used",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isAr)
                            "تُستخدم البيانات فقط لغرض الرد على استفسارك التجاري، وتعيين المستشار المختص، وإعداد عروض الخدمات. لا نقوم ببيع أو مشاركة بياناتك مع أي طرف ثالث لأغراض تسويقية."
                        else
                            "Your data is used solely to evaluate your business inquiry, assign the appropriate Saudi corporate advisor, prepare preliminary feasibility or quotations, and facilitate your requested communication method. We never sell, monetize, or disclose your corporate information.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = if (isAr) "3. الأمان وحفظ البيانات" else "3. Security & Retention",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isAr)
                            "يتم تخزين طلبات الاستشارة محلياً على جهازك بشكل آمن، وتنقل كافة الاتصالات عبر بروتوكولات التشفير القياسية (HTTPS / SSL)."
                        else
                            "Consultation records are stored securely on-device with Room, and all external transmissions follow industry-standard encrypted channels (HTTPS).",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = if (isAr) "4. التواصل والاستفسارات" else "4. Contact Us",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Al Tasheer Mena\nOffice No.1, Al Oudaba Street, Near Naseem Polyclinic, Jeddah, Saudi Arabia\nEmail: enquiry@tasheermena.com\nWebsite: https://www.tasheermena.com/",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
