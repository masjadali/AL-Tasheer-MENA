package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.AppLanguage
import com.example.data.model.FaqItem
import com.example.data.model.IndustryItem
import com.example.data.model.LeadEntity
import com.example.data.model.ServiceItem
import com.example.data.model.TestimonialItem
import com.example.data.model.TimelineStep
import com.example.data.repository.TasheerRepository
import com.example.util.AnalyticsTracker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ConsultationFormState(
    val fullName: String = "",
    val companyName: String = "",
    val email: String = "",
    val phone: String = "",
    val country: String = "Saudi Arabia",
    val serviceRequired: String = "Company Formation",
    val industry: String = "Technology",
    val message: String = "",
    val preferredContactMethod: String = "WhatsApp",
    val isSubmitting: Boolean = false,
    val submissionSuccess: Boolean = false,
    val lastSubmittedLead: LeadEntity? = null,
    val validationError: String? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TasheerRepository

    init {
        val db = AppDatabase.getInstance(application)
        repository = TasheerRepository(db.leadDao())
    }

    private val _language = MutableStateFlow(AppLanguage.ENGLISH)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    private val _currentTab = MutableStateFlow(0)
    val currentTab: StateFlow<Int> = _currentTab.asStateFlow()

    private val _selectedServiceId = MutableStateFlow<String?>(null)
    val selectedServiceId: StateFlow<String?> = _selectedServiceId.asStateFlow()

    private val _selectedIndustryId = MutableStateFlow<String?>(null)
    val selectedIndustryId: StateFlow<String?> = _selectedIndustryId.asStateFlow()

    private val _isConsultationModalOpen = MutableStateFlow(false)
    val isConsultationModalOpen: StateFlow<Boolean> = _isConsultationModalOpen.asStateFlow()

    private val _isPrivacyPolicyOpen = MutableStateFlow(false)
    val isPrivacyPolicyOpen: StateFlow<Boolean> = _isPrivacyPolicyOpen.asStateFlow()

    private val _formState = MutableStateFlow(ConsultationFormState())
    val formState: StateFlow<ConsultationFormState> = _formState.asStateFlow()

    val leads: StateFlow<List<LeadEntity>> = repository.allLeads.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val services: List<ServiceItem> = repository.getServices()
    val industries: List<IndustryItem> = repository.getIndustries()
    val timelineSteps: List<TimelineStep> = repository.getHowItWorksSteps()
    val testimonials: List<TestimonialItem> = repository.getTestimonials()
    val trustedClients: List<String> = repository.getTrustedClients()
    val faqs: List<FaqItem> = repository.getFaqs()

    fun switchLanguage(lang: AppLanguage) {
        _language.value = lang
        AnalyticsTracker.logEvent("language_change", mapOf("language" to lang.code))
    }

    fun selectTab(tab: Int) {
        _currentTab.value = tab
        // Clear sub-destinations when switching tabs
        _selectedServiceId.value = null
        _selectedIndustryId.value = null
        _isPrivacyPolicyOpen.value = false
        AnalyticsTracker.logEvent("tab_select", mapOf("tab_index" to tab))
    }

    fun openServiceDetail(serviceId: String) {
        _selectedServiceId.value = serviceId
        AnalyticsTracker.logEvent("service_view", mapOf("service_id" to serviceId))
    }

    fun closeServiceDetail() {
        _selectedServiceId.value = null
    }

    fun openIndustryDetail(industryId: String) {
        _selectedIndustryId.value = industryId
        AnalyticsTracker.logEvent("industry_view", mapOf("industry_id" to industryId))
    }

    fun closeIndustryDetail() {
        _selectedIndustryId.value = null
    }

    fun openPrivacyPolicy() {
        _isPrivacyPolicyOpen.value = true
        AnalyticsTracker.logEvent("privacy_policy_open")
    }

    fun closePrivacyPolicy() {
        _isPrivacyPolicyOpen.value = false
    }

    fun openConsultationModal(service: String = "", industry: String = "") {
        _formState.value = _formState.value.copy(
            serviceRequired = if (service.isNotEmpty()) service else _formState.value.serviceRequired,
            industry = if (industry.isNotEmpty()) industry else _formState.value.industry,
            submissionSuccess = false,
            validationError = null
        )
        _isConsultationModalOpen.value = true
        AnalyticsTracker.logEvent("consultation_form_open", mapOf("service" to service, "industry" to industry))
    }

    fun closeConsultationModal() {
        _isConsultationModalOpen.value = false
    }

    fun updateFormFields(
        fullName: String? = null,
        companyName: String? = null,
        email: String? = null,
        phone: String? = null,
        country: String? = null,
        serviceRequired: String? = null,
        industry: String? = null,
        message: String? = null,
        preferredContactMethod: String? = null
    ) {
        _formState.value = _formState.value.copy(
            fullName = fullName ?: _formState.value.fullName,
            companyName = companyName ?: _formState.value.companyName,
            email = email ?: _formState.value.email,
            phone = phone ?: _formState.value.phone,
            country = country ?: _formState.value.country,
            serviceRequired = serviceRequired ?: _formState.value.serviceRequired,
            industry = industry ?: _formState.value.industry,
            message = message ?: _formState.value.message,
            preferredContactMethod = preferredContactMethod ?: _formState.value.preferredContactMethod,
            validationError = null
        )
    }

    fun submitConsultation() {
        val state = _formState.value
        if (state.fullName.isBlank() || state.companyName.isBlank() || state.email.isBlank() || state.phone.isBlank()) {
            _formState.value = state.copy(
                validationError = if (_language.value == AppLanguage.ARABIC)
                    "يرجى استكمال جميع الحقول الإلزامية (*)"
                else
                    "Please complete all mandatory fields (*)"
            )
            return
        }

        if (!state.email.contains("@") || !state.email.contains(".")) {
            _formState.value = state.copy(
                validationError = if (_language.value == AppLanguage.ARABIC)
                    "يرجى إدخال عنوان بريد إلكتروني صالح"
                else
                    "Please enter a valid email address"
            )
            return
        }

        viewModelScope.launch {
            _formState.value = state.copy(isSubmitting = true, validationError = null)
            try {
                val lead = repository.submitLead(
                    fullName = state.fullName.trim(),
                    companyName = state.companyName.trim(),
                    email = state.email.trim(),
                    phone = state.phone.trim(),
                    country = state.country.trim(),
                    serviceRequired = state.serviceRequired,
                    industry = state.industry,
                    message = state.message.trim(),
                    preferredContactMethod = state.preferredContactMethod
                )
                AnalyticsTracker.logEvent("consultation_submit", mapOf("lead_id" to lead.leadReference))
                _formState.value = _formState.value.copy(
                    isSubmitting = false,
                    submissionSuccess = true,
                    lastSubmittedLead = lead
                )
            } catch (e: Exception) {
                _formState.value = _formState.value.copy(
                    isSubmitting = false,
                    validationError = e.localizedMessage ?: "Failed to record consultation request"
                )
            }
        }
    }

    fun resetForm() {
        _formState.value = ConsultationFormState()
    }
}
