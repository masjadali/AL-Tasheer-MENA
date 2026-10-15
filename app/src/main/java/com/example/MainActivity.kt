package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.AppLanguage
import com.example.ui.components.ConsultationBottomSheet
import com.example.ui.components.FloatingContactMenu
import com.example.ui.components.TasheerBottomNav
import com.example.ui.components.TasheerTopBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.IndustriesScreen
import com.example.ui.screens.IndustryDetailScreen
import com.example.ui.screens.PrivacyPolicyScreen
import com.example.ui.screens.ServiceDetailScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.theme.TasheerMenaTheme
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TasheerMenaTheme {
                TasheerMenaApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TasheerMenaApp(viewModel: MainViewModel) {
    val language by viewModel.language.collectAsStateWithLifecycle()
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val selectedServiceId by viewModel.selectedServiceId.collectAsStateWithLifecycle()
    val selectedIndustryId by viewModel.selectedIndustryId.collectAsStateWithLifecycle()
    val isConsultationModalOpen by viewModel.isConsultationModalOpen.collectAsStateWithLifecycle()
    val isPrivacyPolicyOpen by viewModel.isPrivacyPolicyOpen.collectAsStateWithLifecycle()
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val leads by viewModel.leads.collectAsStateWithLifecycle()

    val layoutDirection = if (language.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        val showBottomBar = selectedServiceId == null && selectedIndustryId == null && !isPrivacyPolicyOpen

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (showBottomBar) {
                    TasheerTopBar(
                        currentLanguage = language,
                        onLanguageToggle = { viewModel.switchLanguage(it) },
                        onOpenContact = { viewModel.selectTab(4) }
                    )
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    TasheerBottomNav(
                        selectedTab = currentTab,
                        onTabSelected = { viewModel.selectTab(it) },
                        language = language
                    )
                }
            },
            floatingActionButton = {
                if (showBottomBar && currentTab != 4) {
                    FloatingContactMenu(
                        language = language,
                        onOpenConsultation = { viewModel.openConsultationModal() }
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when {
                    isPrivacyPolicyOpen -> {
                        PrivacyPolicyScreen(
                            language = language,
                            onBack = { viewModel.closePrivacyPolicy() }
                        )
                    }
                    selectedServiceId != null -> {
                        val service = viewModel.services.find { it.id == selectedServiceId }
                        if (service != null) {
                            ServiceDetailScreen(
                                service = service,
                                language = language,
                                onBack = { viewModel.closeServiceDetail() },
                                onRequestConsultation = { srv, ind ->
                                    viewModel.openConsultationModal(srv, ind)
                                }
                            )
                        } else {
                            viewModel.closeServiceDetail()
                        }
                    }
                    selectedIndustryId != null -> {
                        val industry = viewModel.industries.find { it.id == selectedIndustryId }
                        if (industry != null) {
                            IndustryDetailScreen(
                                industry = industry,
                                language = language,
                                onBack = { viewModel.closeIndustryDetail() },
                                onRequestConsultation = { srv, ind ->
                                    viewModel.openConsultationModal(srv, ind)
                                }
                            )
                        } else {
                            viewModel.closeIndustryDetail()
                        }
                    }
                    else -> {
                        Crossfade(targetState = currentTab, label = "tab_crossfade") { tab ->
                            when (tab) {
                                0 -> HomeScreen(
                                    services = viewModel.services,
                                    industries = viewModel.industries,
                                    timelineSteps = viewModel.timelineSteps,
                                    testimonials = viewModel.testimonials,
                                    trustedClients = viewModel.trustedClients,
                                    faqs = viewModel.faqs,
                                    language = language,
                                    onNavigateToServices = { viewModel.selectTab(1) },
                                    onNavigateToIndustries = { viewModel.selectTab(2) },
                                    onSelectService = { viewModel.openServiceDetail(it) },
                                    onSelectIndustry = { viewModel.openIndustryDetail(it) },
                                    onRequestConsultation = { srv, ind ->
                                        viewModel.openConsultationModal(srv, ind)
                                    }
                                )
                                1 -> ServicesScreen(
                                    services = viewModel.services,
                                    language = language,
                                    onSelectService = { viewModel.openServiceDetail(it) },
                                    onRequestConsultation = { srv, ind ->
                                        viewModel.openConsultationModal(srv, ind)
                                    }
                                )
                                2 -> IndustriesScreen(
                                    industries = viewModel.industries,
                                    language = language,
                                    onSelectIndustry = { viewModel.openIndustryDetail(it) },
                                    onRequestConsultation = { srv, ind ->
                                        viewModel.openConsultationModal(srv, ind)
                                    }
                                )
                                3 -> AboutScreen(
                                    trustedClients = viewModel.trustedClients,
                                    language = language,
                                    onOpenConsultation = { viewModel.openConsultationModal() },
                                    onOpenPrivacyPolicy = { viewModel.openPrivacyPolicy() }
                                )
                                4 -> ContactScreen(
                                    formState = formState,
                                    onFieldChange = { fn, cn, em, ph, co, sr, ind, msg, pcm ->
                                        viewModel.updateFormFields(fn, cn, em, ph, co, sr, ind, msg, pcm)
                                    },
                                    onSubmit = { viewModel.submitConsultation() },
                                    leads = leads,
                                    language = language
                                )
                            }
                        }
                    }
                }
            }
        }

        // Global Consultation Bottom Sheet Modal
        ConsultationBottomSheet(
            isOpen = isConsultationModalOpen,
            onDismiss = { viewModel.closeConsultationModal() },
            formState = formState,
            onFieldChange = { fn, cn, em, ph, co, sr, ind, msg, pcm ->
                viewModel.updateFormFields(fn, cn, em, ph, co, sr, ind, msg, pcm)
            },
            onSubmit = { viewModel.submitConsultation() },
            language = language
        )
    }
}
