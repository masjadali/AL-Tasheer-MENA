package com.example.ui.screens

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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.LeadEntity
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.NavyVariant
import com.example.ui.theme.SaudiGreen
import com.example.ui.theme.SlateLightBorder
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.viewmodel.ConsultationFormState
import com.example.util.IntentUtils
import com.example.util.Localization

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ContactScreen(
    formState: ConsultationFormState,
    onFieldChange: (
        fullName: String?,
        companyName: String?,
        email: String?,
        phone: String?,
        country: String?,
        serviceRequired: String?,
        industry: String?,
        message: String?,
        preferredContactMethod: String?
    ) -> Unit,
    onSubmit: () -> Unit,
    leads: List<LeadEntity>,
    language: AppLanguage
) {
    val context = LocalContext.current
    val isAr = language == AppLanguage.ARABIC
    var selectedContactTab by remember { mutableIntStateOf(0) } // 0: Form & Direct, 1: My Consultation Log

    val serviceOptions = listOf(
        "Company Formation & Related Services",
        "Manpower Solutions",
        "Financial Advisory",
        "Governmental & Regulatory Compliance",
        "Food Division",
        "Logistics",
        "Construction",
        "Landscaping",
        "Other Corporate Services"
    )

    val industryOptions = listOf(
        "Technology",
        "Manufacturing",
        "Logistics",
        "Hospitality",
        "Healthcare",
        "Real Estate",
        "Retail",
        "Government",
        "Other"
    )

    var serviceExpanded by remember { mutableStateOf(false) }
    var industryExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("contact_screen_list"),
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
                    text = Localization.navContact(language),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isAr)
                        "تواصل مباشرة مع المستشارين عبر واتساب، الهاتف، أو إرسال طلب استشارة رسمي"
                    else
                        "Connect directly with certified Saudi corporate advisors via WhatsApp, phone, email or consultation request",
                    style = MaterialTheme.typography.bodySmall,
                    color = GoldLight,
                    lineHeight = 18.sp
                )
            }
        }

        // Sub Tabs: Request Consultation vs Logged Inquiries
        item {
            TabRow(
                selectedTabIndex = selectedContactTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = NavyPrimary
            ) {
                Tab(
                    selected = selectedContactTab == 0,
                    onClick = { selectedContactTab = 0 },
                    text = {
                        Text(
                            text = if (isAr) "طلب استشارة والتواصل" else "Consultation & Contact",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                )
                Tab(
                    selected = selectedContactTab == 1,
                    onClick = { selectedContactTab = 1 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isAr) "طلباتي السابقة" else "My Requests",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            if (leads.isNotEmpty()) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(GoldPrimary)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "${leads.size}",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = NavyPrimary
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }

        if (selectedContactTab == 0) {
            // DIRECT CONTACT ACTION CARDS
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // WhatsApp Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                IntentUtils.openWhatsApp(
                                    context,
                                    if (isAr)
                                        "مرحباً التأشير مينا، أود الاستفسار عن خدماتكم للأعمال في المملكة العربية السعودية."
                                    else
                                        "Hello Al Tasheer Mena, I would like to learn more about your business services in Saudi Arabia."
                                )
                            }
                            .testTag("contact_whatsapp_card"),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, WhatsAppGreen.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(WhatsAppGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "WhatsApp",
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isAr) "المحادثة عبر واتساب" else "Official WhatsApp Channel",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = IntentUtils.OFFICIAL_PHONE_DISPLAY,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF14532D),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Button(
                                onClick = {
                                    IntentUtils.openWhatsApp(
                                        context,
                                        if (isAr)
                                            "مرحباً التأشير مينا، أود الاستفسار عن خدماتكم للأعمال في المملكة العربية السعودية."
                                        else
                                            "Hello Al Tasheer Mena, I would like to learn more about your business services in Saudi Arabia."
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Chat", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Direct Phone Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { IntentUtils.dialPhone(context) }
                            .testTag("contact_phone_card"),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(NavyPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = "Call",
                                    tint = GoldLight,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isAr) "الاتصال الهاتفي المباشر" else "Direct Corporate Line",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = IntentUtils.OFFICIAL_PHONE_DISPLAY,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            OutlinedButton(
                                onClick = { IntentUtils.dialPhone(context) },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Call", fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Email Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { IntentUtils.sendEmail(context) }
                            .testTag("contact_email_card"),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(NavyPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "Email",
                                    tint = GoldLight,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isAr) "البريد الإلكتروني الرسمي" else "Official Corporate Inquiries",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = IntentUtils.OFFICIAL_EMAIL,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            OutlinedButton(
                                onClick = { IntentUtils.sendEmail(context) },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Email", fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Office Location Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { IntentUtils.openOfficeLocation(context) }
                            .testTag("contact_location_card"),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(CircleShape)
                                        .background(NavyPrimary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "Location",
                                        tint = GoldLight,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(14.dp))
                                Column {
                                    Text(
                                        text = Localization.officeLocationTitle(language),
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Jeddah, Kingdom of Saudi Arabia",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = GoldDark,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = IntentUtils.OFFICE_ADDRESS,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 19.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = { IntentUtils.openOfficeLocation(context) },
                                colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .testTag("open_maps_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Map,
                                    contentDescription = null,
                                    tint = GoldLight
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = Localization.openInMaps(language),
                                    color = GoldLight,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // Website Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { IntentUtils.openWebsite(context) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Website",
                                tint = GoldDark,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isAr) "الموقع الإلكتروني الرسمي" else "Official Corporate Website",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "www.tasheermena.com",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // FULL EMBEDDED CONSULTATION REQUEST FORM
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = Localization.getConsultation(language),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isAr) "املأ النموذج وسيتواصل معك مستشارونا لمناقشة متطلباتك" else "Submit your project requirements for an expert feasibility and quotation review",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
                        )

                        if (formState.submissionSuccess && formState.lastSubmittedLead != null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFF0FDF4))
                                    .border(1.dp, SaudiGreen.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                    .padding(16.dp)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = Localization.formSuccessMsg(language),
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF14532D)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Ref: ${formState.lastSubmittedLead.leadReference}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = NavyPrimary
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Button(
                                        onClick = {
                                            val msg = if (isAr) {
                                                "مرحباً التأشير مينا، لقد قمت بتقديم طلب استشارة جديد رقم [${formState.lastSubmittedLead.leadReference}] لخدمة ${formState.lastSubmittedLead.serviceRequired}. أرجو التواصل معي."
                                            } else {
                                                "Hello Al Tasheer Mena, I have submitted consultation request [${formState.lastSubmittedLead.leadReference}] for ${formState.lastSubmittedLead.serviceRequired}. Looking forward to connecting."
                                            }
                                            IntentUtils.openWhatsApp(context, msg)
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(text = Localization.chatOnWhatsApp(language), color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        } else {
                            if (formState.validationError != null) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFEF2F2))
                                        .border(1.dp, Color(0xFFEF4444).copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = formState.validationError,
                                        color = Color(0xFFB91C1C),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // Full Name
                            OutlinedTextField(
                                value = formState.fullName,
                                onValueChange = { onFieldChange(it, null, null, null, null, null, null, null, null) },
                                label = { Text(Localization.formFullName(language)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Company Name
                            OutlinedTextField(
                                value = formState.companyName,
                                onValueChange = { onFieldChange(null, it, null, null, null, null, null, null, null) },
                                label = { Text(Localization.formCompanyName(language)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Email
                            OutlinedTextField(
                                value = formState.email,
                                onValueChange = { onFieldChange(null, null, it, null, null, null, null, null, null) },
                                label = { Text(Localization.formEmail(language)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Phone
                            OutlinedTextField(
                                value = formState.phone,
                                onValueChange = { onFieldChange(null, null, null, it, null, null, null, null, null) },
                                label = { Text(Localization.formPhone(language)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Service Required Dropdown
                            ExposedDropdownMenuBox(
                                expanded = serviceExpanded,
                                onExpandedChange = { serviceExpanded = !serviceExpanded },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = formState.serviceRequired,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text(Localization.formService(language)) },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = serviceExpanded) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                ExposedDropdownMenu(
                                    expanded = serviceExpanded,
                                    onDismissRequest = { serviceExpanded = false }
                                ) {
                                    serviceOptions.forEach { opt ->
                                        DropdownMenuItem(
                                            text = { Text(opt) },
                                            onClick = {
                                                onFieldChange(null, null, null, null, null, opt, null, null, null)
                                                serviceExpanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Industry Dropdown
                            ExposedDropdownMenuBox(
                                expanded = industryExpanded,
                                onExpandedChange = { industryExpanded = !industryExpanded },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = formState.industry,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text(Localization.formIndustry(language)) },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = industryExpanded) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                ExposedDropdownMenu(
                                    expanded = industryExpanded,
                                    onDismissRequest = { industryExpanded = false }
                                ) {
                                    industryOptions.forEach { opt ->
                                        DropdownMenuItem(
                                            text = { Text(opt) },
                                            onClick = {
                                                onFieldChange(null, null, null, null, null, null, opt, null, null)
                                                industryExpanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Message
                            OutlinedTextField(
                                value = formState.message,
                                onValueChange = { onFieldChange(null, null, null, null, null, null, null, it, null) },
                                label = { Text(Localization.formMessage(language)) },
                                minLines = 2,
                                maxLines = 4,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Contact Method Chips
                            Text(
                                text = Localization.formContactMethod(language),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                listOf("WhatsApp", "Phone", "Email").forEach { method ->
                                    val isSelected = formState.preferredContactMethod == method
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { onFieldChange(null, null, null, null, null, null, null, null, method) },
                                        label = { Text(method, fontSize = 12.sp) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = NavyPrimary,
                                            selectedLabelColor = GoldLight
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = onSubmit,
                                enabled = !formState.isSubmitting,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NavyPrimary,
                                    contentColor = GoldLight
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("contact_screen_submit_btn")
                            ) {
                                if (formState.isSubmitting) {
                                    CircularProgressIndicator(
                                        color = GoldLight,
                                        modifier = Modifier.size(24.dp),
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = GoldLight)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = Localization.formSubmit(language),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // MY CONSULTATION REQUESTS LIST
            if (leads.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(NavyPrimary.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = null,
                                tint = NavyPrimary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (isAr) "لا توجد طلبات استشارة سابقة مسجلة" else "No consultation requests logged yet",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (isAr) "الطلبات المقدمة عبر التطبيق سيتم حفظها هنا للمتابعة" else "Any requests submitted will be tracked here for follow-up",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                items(leads) { lead ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SlateLightBorder)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = lead.leadReference,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = NavyPrimary
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(SaudiGreen.copy(alpha = 0.15f))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = lead.status,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = SaudiGreen,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = "${lead.fullName} • ${lead.companyName}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Text(
                                    text = "Service: ${lead.serviceRequired} | Sector: ${lead.industry}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    OutlinedButton(
                                        onClick = {
                                            val msg = "Hello Al Tasheer Mena, following up on consultation request [${lead.leadReference}]."
                                            IntentUtils.openWhatsApp(context, msg)
                                        },
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("WhatsApp Follow-up", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
