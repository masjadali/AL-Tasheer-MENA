package com.example.data.repository

import com.example.data.local.LeadDao
import com.example.data.model.FaqItem
import com.example.data.model.IndustryItem
import com.example.data.model.LeadEntity
import com.example.data.model.ServiceItem
import com.example.data.model.TestimonialItem
import com.example.data.model.TimelineStep
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class TasheerRepository(private val leadDao: LeadDao) {

    val allLeads: Flow<List<LeadEntity>> = leadDao.getAllLeads()

    suspend fun submitLead(
        fullName: String,
        companyName: String,
        email: String,
        phone: String,
        country: String,
        serviceRequired: String,
        industry: String,
        message: String,
        preferredContactMethod: String
    ): LeadEntity {
        val reference = "ATM-" + UUID.randomUUID().toString().take(6).uppercase()
        val entity = LeadEntity(
            leadReference = reference,
            fullName = fullName,
            companyName = companyName,
            email = email,
            phone = phone,
            country = country,
            serviceRequired = serviceRequired,
            industry = industry,
            message = message,
            preferredContactMethod = preferredContactMethod,
            status = "New"
        )
        val id = leadDao.insertLead(entity)
        return entity.copy(id = id)
    }

    suspend fun deleteLead(lead: LeadEntity) {
        leadDao.deleteLead(lead)
    }

    fun getServices(): List<ServiceItem> = listOf(
        ServiceItem(
            id = "company-formation",
            titleEn = "Company Formation & Related Services",
            titleAr = "تأسيس الشركات والخدمات ذات الصلة",
            taglineEn = "End-to-End Business Setup & MISA Licensing in Saudi Arabia",
            taglineAr = "تأسيس الأعمال الشامل وترخيص وزارة الاستثمار بالمملكة",
            descriptionEn = "Navigate the Kingdom's evolving corporate landscape with full legal and regulatory backing. We manage your market entry from strategic structuring and MISA foreign investment licensing to Commercial Registration (CR), corporate bylaws, and commercial bank accounts.",
            descriptionAr = "ندعم دخولكم إلى السوق السعودي عبر حلول قانونية وتنظيمية متكاملة، بدءاً من صياغة الهيكل التجاري وترخيص وزارة الاستثمار (MISA) وصولاً إلى السجل التجاري وعقود التأسيس وفتح الحسابات البنكية.",
            keyBenefitsEn = listOf(
                "100% Foreign Ownership Advisory & MISA License Fast-Tracking",
                "Full Commercial Registration (CR) & Chamber of Commerce Issuance",
                "Articles of Association (AoA) & Ministry of Commerce Notarization",
                "Dedicated Corporate Bank Account Opening Support",
                "Seamless Saudi Market Entry Roadmap"
            ),
            keyBenefitsAr = listOf(
                "استشارات التملك الأجنبي 100% وتسريع تراخيص وزارة الاستثمار",
                "إصدار السجل التجاري والاشتراك بالغرفة التجارية",
                "صياغة عقود التأسيس وتوثيقها بوزارة التجارة",
                "تسهيل فتح الحسابات البنكية للشركات",
                "خطة عمل استراتيجية للدخول إلى السوق السعودي"
            ),
            whatWeProvideEn = listOf(
                "MISA Foreign Investment License Acquisition",
                "Commercial Registration (CR) & Activity Approvals",
                "Ministry of Commerce By-laws Drafting & Registration",
                "Municipality (Balady) & Civil Defense Licensing",
                "Post-incorporation Compliance Mapping & Tax Registration"
            ),
            whatWeProvideAr = listOf(
                "استخراج رخصة الاستثمار الأجنبي من وزارة الاستثمار",
                "إصدار السجل التجاري وموافقات الأنشطة الاقتصادية",
                "صياغة لوائح وعقود التأسيس واعتمادها",
                "رخص البلدية (بلدي) وتصاريح الدفاع المدني",
                "تسجيل الضرائب والامتثال بعد التأسيس"
            ),
            processEn = listOf(
                "Preliminary Consultation & Legal Entity Selection (LLC, Branch, Sole Proprietorship)",
                "Document Notarization, Translation & Apostille Verification",
                "MISA License Filing & Approval Processing",
                "Commercial Registration & Chamber of Commerce Membership",
                "General Manager IQAMA & Corporate Bank Account Activation"
            ),
            processAr = listOf(
                "الاستشارة المبدئية وتحديد نوع الكيان (ذ.م.م، فرع شركة، مؤسسة)",
                "ترجمة الوثائق والتصديق والمصادقة القانونية",
                "تقديم ملف رخصة الاستثمار ومتابعته حتى الاعتماد",
                "استخراج السجل التجاري والاشتراك بالغرفة التجارية",
                "إصدار إقامة المدير العام وتفعيل الحسابات البنكية"
            ),
            relevantIndustriesEn = listOf("Technology", "Manufacturing", "Logistics", "Hospitality", "Healthcare", "Real Estate"),
            relevantIndustriesAr = listOf("التقنية", "الصناعة", "الخدمات اللوجستية", "الضيافة", "الرعاية الصحية", "العقارات"),
            ctaEn = "Start Your Business in Saudi Arabia",
            ctaAr = "ابدأ تأسيس شركتك في المملكة",
            whatsappMessageEn = "Hello Al Tasheer Mena, I am interested in your Company Formation & Business Setup services in Saudi Arabia.",
            whatsappMessageAr = "مرحباً التأشير مينا، أود الاستفسار عن خدمات تأسيس الشركات وبدء الأعمال في المملكة العربية السعودية.",
            iconCategory = "formation"
        ),
        ServiceItem(
            id = "manpower-solutions",
            titleEn = "Manpower Solutions",
            titleAr = "حلول القوى العاملة والكوادر البشرية",
            taglineEn = "Strategic Workforce Sourcing, Visa Processing & HR Management",
            taglineAr = "استقطاب الكفاءات وتأشيرات العمل وإدارة الموارد البشرية",
            descriptionEn = "Scale your operations in Saudi Arabia with qualified technical, specialized, and managerial talent. From visa quotas and processing to outsourced workforce deployment and Saudization compliance (Nitaqat), we empower your business human capital.",
            descriptionAr = "توفير الكوادر المهنية والتقنية والإدارية لدعم توسع أعمالكم بالمملكة. نتولى إدارة التأشيرات المهنية، وتوفير العمالة وتعهيد القوى العاملة، وتحقيق متطلبات التوطين (نطاقات).",
            keyBenefitsEn = listOf(
                "Access to Pre-vetted Blue-collar & White-collar Talent",
                "Full Visa Quota Management & Fast-track Issuance",
                "Complete Saudization (Nitaqat) Compliance Strategy",
                "Payroll, Health Insurance & Labor Accommodation Solutions",
                "Executive Search & Management-level Headhunting"
            ),
            keyBenefitsAr = listOf(
                "توفير كوادر مهنية وإدارية مؤهلة ومختبرة",
                "إدارة رصيد التأشيرات وتسريع إصدارها",
                "خطط متقدمة للامتثال لبرنامج التوطين (نطاقات)",
                "إدارة الرواتب والتأمين الطبي وحلول السكن للعمالة",
                "استقطاب الكفاءات التنفيذية والقيادات العليا"
            ),
            whatWeProvideEn = listOf(
                "Work Visa Sourcing & Block Visa Approvals",
                "End-to-End IQAMA Issuance, Renewals & Transfers",
                "Manpower Outsourcing for Contracting, Hospitality & Retail",
                "HR Contract Administration via Qiwa Platform",
                "Wage Protection System (WPS) Compliance Audits"
            ),
            whatWeProvideAr = listOf(
                "إصدار وتخصيص تأشيرات العمل وتأييدات الرصيد",
                "إصدار الإقامات وتجديدها ونقل الكفالات",
                "تعهيد وتوريد العمالة لقطاعات المقاولات والضيافة والتجزئة",
                "توثيق وإدارة عقود العمل عبر منصة قوى",
                "التدقيق والمتابعة لنظام حماية الأجور (WPS)"
            ),
            processEn = listOf(
                "Workforce Requirements Assessment & Skill Mapping",
                "Ministry of Human Resources Visa Quota Allocation",
                "Global Sourcing, Screening & Medical Clearance",
                "Arrival, Onboarding, Qiwa Registration & IQAMA Processing",
                "Ongoing Operational & Welfare Management"
            ),
            processAr = listOf(
                "تحليل احتياجات القوى العاملة والمؤهلات المطلوبة",
                "تخصيص رصيد التأشيرات من وزارة الموارد البشرية",
                "الاستقطاب الخارجي والفحص المهني والطبي",
                "الاستقبال والتوثيق بمنصة قوى وإصدار الإقامات",
                "الإشراف التشغيلي المستمر والمتابعة الميدانية"
            ),
            relevantIndustriesEn = listOf("Construction", "Hospitality", "Logistics", "Healthcare", "Manufacturing"),
            relevantIndustriesAr = listOf("المقاولات", "الضيافة", "الخدمات اللوجستية", "الرعاية الصحية", "الصناعة"),
            ctaEn = "Request Manpower Consultation",
            ctaAr = "طلب استشارة القوى العاملة",
            whatsappMessageEn = "Hello Al Tasheer Mena, I would like to consult on Manpower Solutions and visa processing for our enterprise.",
            whatsappMessageAr = "مرحباً التأشير مينا، أود استشارة حول حلول القوى العاملة وتأشيرات العمل لمشروعنا.",
            iconCategory = "manpower"
        ),
        ServiceItem(
            id = "financial-advisory",
            titleEn = "Financial Advisory",
            titleAr = "الاستشارات والحلول المالية",
            taglineEn = "Fiscal Excellence, Working Capital Support & Capital Growth",
            taglineAr = "إدارة السيولة واستراتيجيات النمو والرقابة المالية",
            descriptionEn = "Empower corporate leadership with data-driven financial advisory. We assist enterprises with cash flow optimization, working capital restructuring, investment feasibility, feasibility studies for Saudi Vision 2030 initiatives, and financial KPI governance.",
            descriptionAr = "تمكين الإدارة التنفيذية بالرؤى المالية الدقيقة. نقدم خدمات إدارة التدفقات النقدية، وهيكلة رأس المال العامل، ودراسات الجدوى الاقتصادية لمشاريع رؤية المملكة 2030، وحوكمة المؤشرات المالية.",
            keyBenefitsEn = listOf(
                "Cash-flow Planning & Liquidity Optimization",
                "Working Capital Restructuring & Debt Advisory",
                "Feasibility Studies for Industrial & Commercial Projects",
                "Budgeting, Forecasting & Financial Modeling",
                "Corporate Valuation & M&A Due Diligence Support"
            ),
            keyBenefitsAr = listOf(
                "تخطيط التدفقات النقدية وتعزيز السيولة",
                "إعادة هيكلة رأس المال العامل وإدارة التمويل",
                "دراسات الجدوى المعتمدة للمشاريع الصناعية والتجارية",
                "إعداد الميزانيات التقديرية والنمذجة المالية",
                "تقييم الشركات ودعم الفحص المالي النافي للجهالة"
            ),
            whatWeProvideEn = listOf(
                "Strategic CFO Advisory & KPI Dashboard Implementation",
                "Financial Restructuring & Lender Negotiation",
                "Cost Reduction & Profitability Optimization Programs",
                "Saudi Industrial Development Fund (SIDF) Application Advisory",
                "Accounting Policy Formulation & Internal Financial Controls"
            ),
            whatWeProvideAr = listOf(
                "استشارات الإدارة المالية التنفيذية ولوحات المؤشرات",
                "إعادة الهيكلة المالية والتفاوض مع جهات التمويل",
                "برامج ترشيد النفقات وتعظيم الربحية",
                "إعداد ملفات التمويل لصندوق التنمية الصناعي والجهات الداعمة",
                "صياغة السياسات المحاسبية وأنظمة الرقابة الداخلية"
            ),
            processEn = listOf(
                "Comprehensive Diagnostic Financial Health Audit",
                "Cash Flow & Balance Sheet Architecture Analysis",
                "Strategy Formulation & Financial Forecast Modeling",
                "Execution Support with Banking & Financial Partners",
                "Periodic Performance Monitoring & Variance Governance"
            ),
            processAr = listOf(
                "التشخيص والتدقيق المالي الشامل للوضع الحالي",
                "تحليل التدفقات النقدية وهيكلية الميزانية",
                "صياغة الاستراتيجية وبناء النماذج المالية",
                "دعم التنفيذ والتنسيق مع البنوك والمؤسسات التمويلية",
                "المتابعة الدورية ومراقبة مؤشرات الأداء والانحرافات"
            ),
            relevantIndustriesEn = listOf("Manufacturing", "Real Estate", "Retail", "Technology", "Logistics"),
            relevantIndustriesAr = listOf("الصناعة", "العقارات", "التجزئة", "التقنية", "اللوجستيات"),
            ctaEn = "Talk to a Financial Advisor",
            ctaAr = "تحدث مع مستشار مالي",
            whatsappMessageEn = "Hello Al Tasheer Mena, I would like to schedule a session with a Financial Advisor regarding our business.",
            whatsappMessageAr = "مرحباً التأشير مينا، أود حجز جلسة استشارة مع مستشار مالي لمؤسستنا.",
            iconCategory = "finance"
        ),
        ServiceItem(
            id = "governmental-compliance",
            titleEn = "Governmental & Regulatory Compliance",
            titleAr = "الامتثال الحكومي والتنظيمي",
            taglineEn = "Full Mastery of Qiwa, Muqeem, ZATCA, Balady & Official Portals",
            taglineAr = "إدارة شاملة لمنصات قوى، مقيم، زاتكا، وبلدي والجهات الحكومية",
            descriptionEn = "Eliminate bureaucratic friction and operational bottlenecks. We manage all government relations, permits, municipal licensing (Balady), ZATCA e-invoicing and tax compliance, GOSI, Qiwa, and Muqeem accounts to keep your operations 100% compliant.",
            descriptionAr = "تجاوز التحديات الإجرائية وضمان الامتثال التام للأنظمة السعودية. نتولى إدارة العلاقات الحكومية، والتراخيص البلدية (بلدي)، والامتثال للفوترة الإلكترونية والزكاة والضريبة (ZATCA)، ومنصات قوى ومقيم والتأمينات الاجتماعية.",
            keyBenefitsEn = listOf(
                "Zero Penalties via Proactive Regulatory Auditing",
                "Direct Relationship Management with Ministries & Authorities",
                "Complete Portal Administration (Qiwa, Muqeem, Balady, ZATCA, GOSI)",
                "ZATCA E-Invoicing Phase 2 Compliance Certification",
                "Specialized Factory, Commercial & Industrial Permits"
            ),
            keyBenefitsAr = listOf(
                "تجنب الغرامات عبر المراجعة الاستباقية للوائح",
                "إدارة العلاقات والمتابعة المباشرة مع الوزارات والهيئات",
                "إدارة متكاملة للمنصات الحكومية (قوى، مقيم، بلدي، زاتكا، التأمينات)",
                "الامتثال لمتطلبات الفوترة الإلكترونية (المرحلة الثانية - زاتكا)",
                "استخراج تراخيص المصانع والمنشآت التجارية والصناعية"
            ),
            whatWeProvideEn = listOf(
                "Corporate Government Relations Officer (GRO) Representation",
                "Zakat, Tax & Customs Authority (ZATCA) Filing & Clearance",
                "General Organization for Social Insurance (GOSI) Governance",
                "Municipal (Balady) Operational License Renewals & Inspections",
                "Civil Defense Safety Certificate Approvals (Salama)"
            ),
            whatWeProvideAr = listOf(
                "خدمات التعقيب والعلاقات الحكومية المؤسسية المعتمدة",
                "تقديم الإقرارات والشهادات لدى هيئة الزكاة والضريبة والجمارك",
                "إدارة ملفات التأمينات الاجتماعية والاشتراكات",
                "إصدار وتجديد رخص بلدي وجولات التفتيش",
                "شهادات السلامة والتصاريح من الدفاع المدني (سلامة)"
            ),
            processEn = listOf(
                "Enterprise Compliance Health Check & Deficiency Audit",
                "Account Setup & Authority Portal Integration",
                "Resolving Outstanding Filings & Regulatory Holds",
                "Automated Renewal Alerts & Proactive Filings",
                "Monthly Compliance Status Reporting"
            ),
            processAr = listOf(
                "فحص شامل للامتثال وتحديد المتطلبات المعلقة",
                "ربط وتفويض الحسابات بالبوابات الحكومية المعتمدة",
                "معالجة الملاحظات والمخالفات العالقة إن وجدت",
                "المتابعة الدورية وإدارة التجديدات مسبقاً",
                "إصدار تقارير الامتثال الشهرية الدورية"
            ),
            relevantIndustriesEn = listOf("Government", "Healthcare", "Manufacturing", "Retail", "Hospitality"),
            relevantIndustriesAr = listOf("القطاع الحكومي", "الرعاية الصحية", "الصناعة", "التجزئة", "الضيافة"),
            ctaEn = "Request Compliance Support",
            ctaAr = "طلب دعم الامتثال الحكومي",
            whatsappMessageEn = "Hello Al Tasheer Mena, we need assistance with Governmental & Regulatory Compliance in Saudi Arabia.",
            whatsappMessageAr = "مرحباً التأشير مينا، نحتاج مساعدة في الامتثال الحكومي والتراخيص التنظيمية بالمملكة.",
            iconCategory = "compliance"
        ),
        ServiceItem(
            id = "food-division",
            titleEn = "Food Division",
            titleAr = "قطاع الأغذية والتموين",
            taglineEn = "Supply Chain, SFDA Registration & International Brand Distribution",
            taglineAr = "سلاسل الإمداد الغذائي، تسجيل هيئة الغذاء والدواء، والوكالات التجارية",
            descriptionEn = "Expand your food and beverage brand in the MENA region's largest consumer market. We manage food supply chains, SFDA product classification and laboratory clearance, Halal compliance, commercial cold-chain distribution, and food facility licensing.",
            descriptionAr = "تطوير أعمال الأغذية والمشروبات في أكبر سوق استهلاكي بالمنطقة. نقدم خدمات سلاسل الإمداد الغذائي، وتسجيل وتصنيف المنتجات لدى هيئة الغذاء والدواء (SFDA)، وشهادات الحلال، والتوزيع التجاري المبرد وترخيص المنشآت الغذائية.",
            keyBenefitsEn = listOf(
                "SFDA Product Registration & Import Clearance Expertise",
                "Halal Verification & International Standards Certification",
                "Established Saudi FMCG Distribution Channels",
                "Central Kitchen & Restaurant Branch Regulatory Setup",
                "Temperature-controlled Cold Storage & Logistical Linkages"
            ),
            keyBenefitsAr = listOf(
                "تسجيل المنتجات الغذائية والفسح الجمركي بهيئة الغذاء والدواء",
                "شهادات المطابقة واعتماد معايير الحلال الدولية",
                "شبكات توزيع وتوريد معتمدة لقطاع التجزئة والمطاعم",
                "تأسيس وترخيص المطابخ المركزية وسلاسل المطاعم",
                "ربط لوجستي بمستودعات مبردة ومطابقة للاشتراطات"
            ),
            whatWeProvideEn = listOf(
                "Saudi Food & Drug Authority (SFDA) Account Management & FADSO",
                "Product Formulation Review for Saudi Standards Compliance",
                "Commercial Distribution Partnerships with Supermarkets & HoReCa",
                "Customs Food Clearance at Jeddah Islamic Port & King Abdulaziz Port",
                "Food Safety Management System (HACCP / ISO 22000) Advisory"
            ),
            whatWeProvideAr = listOf(
                "إدارة حسابات منشأة الأغذية لدى الهيئة العامة للغذاء والدواء",
                "مراجعة بطاقة ومكونات المنتجات ومطابقتها للمواصفات السعودية",
                "شراكات التوزيع مع سلاسل السوبرماركت وقطاع الفنادق والمطاعم",
                "الفسح الجمركي للأغذية بميناء جدة الإسلامي وميناء الملك عبدالعزيز",
                "استشارات نظم سلامة الغذاء (HACCP و ISO 22000)"
            ),
            processEn = listOf(
                "Ingredient & Label Compliance Review under SASO / SFDA Rules",
                "Laboratory Testing & Health Certificate Authentication",
                "Import License Issuance & Port Cargo Inspection",
                "Storage Facility Audit & Transportation Fleet Alignment",
                "Retail & Commercial Market Channel Deployment"
            ),
            processAr = listOf(
                "مراجعة المكونات والملصق الغذائي وفق لوائح المواصفات والغذاء",
                "الفحص المخبري وتوثيق الشهادات الصحية",
                "إصدار إذن الاستيراد وإجراءات المعاينة بالموانئ",
                "التأكد من جاهزية المستودعات وأسطول النقل المبرد",
                "التوزيع في منافذ البيع وقنوات السوق التجاري"
            ),
            relevantIndustriesEn = listOf("Hospitality", "Retail", "Manufacturing", "Logistics"),
            relevantIndustriesAr = listOf("الضيافة", "التجزئة", "الصناعة", "الخدمات اللوجستية"),
            ctaEn = "Discuss Food Business",
            ctaAr = "مناقشة مشاريع قطاع الأغذية",
            whatsappMessageEn = "Hello Al Tasheer Mena, I would like to discuss our Food Division & SFDA business requirements.",
            whatsappMessageAr = "مرحباً التأشير مينا، أود مناقشة متطلبات قطاع الأغذية وتراخيص هيئة الغذاء والدواء لمشروعنا.",
            iconCategory = "food"
        ),
        ServiceItem(
            id = "logistics",
            titleEn = "Logistics",
            titleAr = "الخدمات اللوجستية وسلاسل الإمداد",
            taglineEn = "Customs Clearance, Freight Forwarding & Nationwide Distribution",
            taglineAr = "التخليص الجمركي، الشحن الدولي، والتوزيع الشامل بالمملكة",
            descriptionEn = "Streamline your global and domestic cargo movement across Saudi Arabia. We handle customs clearance at all major Saudi seaports, airports, and land borders (Fasah portal integration), multimodal freight forwarding, bonded warehousing, and last-mile delivery.",
            descriptionAr = "تحسين حركة البضائع دولياً ومحلياً داخل المملكة. نقدم حلول التخليص الجمركي عبر كافة الموانئ والمطارات والمنافذ البرية (منصة فسح)، والشحن المتعدد، والتخزين الجمركي، وشبكات النقل والتوزيع الموثوقة.",
            keyBenefitsEn = listOf(
                "Expedited Port Customs Clearance via Fasah System",
                "Sea, Air & Land Freight Forwarding with Global Agents",
                "Strategic Bonded & Dry Warehousing in Riyadh & Jeddah",
                "SABER Platform Product Conformity Certification Support",
                "Real-time Shipment Tracking & Cargo Documentation"
            ),
            keyBenefitsAr = listOf(
                "تخليص جمركي سريع بالموانئ والمنافذ عبر منصة فسح",
                "شحن بحري وجوي وبري بالتعاون مع كبرى الوكالات العالمية",
                "مستودعات تخزين جافة وجمركية استراتيجية في الرياض وجدة",
                "إصدار شهادات مطابقة المنتجات عبر منصة سابر (SABER)",
                "متابعة الشحنات وتوثيق مستندات النقل والجمارك"
            ),
            whatWeProvideEn = listOf(
                "Customs Brokerage at Jeddah Islamic Port, King Abdullah Port & Dammam",
                "Air Cargo Clearance at King Abdulaziz (JED) & King Khalid (RUH) Airports",
                "Overland Cross-border Clearance (Batha, Salwa, Haditha)",
                "Local Fleet Distribution across Central, Western & Eastern Provinces",
                "Dangerous Goods (DG) & Temperature-Sensitive Cargo Handling"
            ),
            whatWeProvideAr = listOf(
                "التخليص الجمركي بميناء جدة، وميناء الملك عبدالله، وميناء الدمام",
                "الشحن والتخليص الجوي بمطارات الرياض وجدة والدمام",
                "التخليص بالمنافذ البرية (البطحاء، سلوى، الحديثة)",
                "أسطول نقل وتوزيع يغطي كافة مناطق المملكة الرئيسية",
                "التعامل مع البضائع الخاصة والحساسة والمواد الخاضعة للاشتراطات"
            ),
            processEn = listOf(
                "Documentation Review (Bill of Lading, Invoice, Packing List, Certificate of Origin)",
                "HS Code Verification & SABER Conformity Certificate Linkage",
                "Fasah Electronic Bayan Declaration Filing",
                "Inspection, Customs Duty Payment & Container Release",
                "Secure Transportation to Client Warehouse or Project Site"
            ),
            processAr = listOf(
                "تدقيق المستندات (البوليصة، الفاتورة، قائمة التعبئة، شهادة المنشأ)",
                "تحديد رمز النظام المنسق (HS Code) والربط مع شهادات سابر",
                "إعداد وتقديم البيان الجمركي عبر نظام فسح الإلكتروني",
                "المعاينة وسداد الرسوم الجمركية والفسح الفوري للحاويات",
                "النقل الآمن للمستودعات أو موقع المشروع"
            ),
            relevantIndustriesEn = listOf("Manufacturing", "Retail", "Technology", "Healthcare", "Construction"),
            relevantIndustriesAr = listOf("الصناعة", "التجزئة", "التقنية", "الرعاية الصحية", "المقاولات"),
            ctaEn = "Request Logistics Consultation",
            ctaAr = "طلب استشارة لوجستية",
            whatsappMessageEn = "Hello Al Tasheer Mena, I need assistance with Logistics and Customs Clearance in Saudi Arabia.",
            whatsappMessageAr = "مرحباً التأشير مينا، أحتاج دعماً في الخدمات اللوجستية والتخليص الجمركي بالمملكة.",
            iconCategory = "logistics"
        ),
        ServiceItem(
            id = "construction",
            titleEn = "Construction",
            titleAr = "المقاولات والإنشاءات",
            taglineEn = "Project Management, Commercial Facilities & Contracting",
            taglineAr = "إدارة المشاريع، المنشآت التجارية، وأعمال المقاولات المتكاملة",
            descriptionEn = "Deliver your commercial, industrial, and infrastructure developments with uncompromising engineering standards. We provide turnkey general contracting, facility engineering, MEP installations, project lifecycle management, and municipal building approvals across the Kingdom.",
            descriptionAr = "تنفيذ مشاريعكم التجارية والصناعية والبنية التحتية بأعلى المعايير الهندسية. نقدم حلول المقاولات العامة المتكاملة، وتطوير المنشآت، والأعمال الكهروميكانيكية (MEP)، وإدارة دورة حياة المشروع واستخراج رخص البناء البلدية.",
            keyBenefitsEn = listOf(
                "Turnkey Commercial & Industrial Project Execution",
                "Balady Building Permits & Engineering Office Approvals",
                "MEP (Mechanical, Electrical, Plumbing) Coordination",
                "Strict Safety Compliance & Civil Defense Sign-off",
                "Cost Engineering, Quantity Surveying & Milestone Governance"
            ),
            keyBenefitsAr = listOf(
                "تنفيذ متكامل للمشاريع التجارية والصناعية تسليم مفتاح",
                "استخراج رخص البناء والاعتمادات الهندسية عبر منصة بلدي",
                "الأعمال الكهروميكانيكية المتكاملة وتجهيزات البنية التحتية",
                "الالتزام الصارم باشتراطات السلامة واعتمادات الدفاع المدني",
                "هندسة القيمة وحساب الكميات والالتزام بالجداول الزمنية"
            ),
            whatWeProvideEn = listOf(
                "General Contracting for Warehouses, Showrooms & Offices",
                "Fit-out & Interior Architectural Contracting",
                "Civil Engineering, Structural Steel & Foundation Works",
                "HVAC, Firefighting & Electrical Infrastructure Deployment",
                "Contractor Classification & Tender Pre-qualification Advisory"
            ),
            whatWeProvideAr = listOf(
                "المقاولات العامة للمستودعات والمعارض والمقرات الإدارية",
                "التشطيبات المعمارية والديكور الداخلي للمنشآت",
                "الأعمال المدنية والهياكل الحديدية والخرسانية",
                "شبكات التكييف المركزي وأنظمة مكافحة الحريق والكهرباء",
                "تأهيل المقاولين وتصنيف الشركات للمنافسات والمناقصات"
            ),
            processEn = listOf(
                "Site Survey, Architectural Design Review & Feasibility",
                "Municipal Permitting (Balady) & Engineering Office Approval",
                "Structural, Civil & MEP Phased Construction Execution",
                "Quality Inspection, Safety Audits & Client Walkthroughs",
                "Final Completion Certificate (Ithbat Injaz) & Building Handover"
            ),
            processAr = listOf(
                "المعاينة الميدانية ومراجعة المخططات الهندسية والجدوى",
                "استخراج تصاريح البناء من بلدي والجهات المعنية",
                "تنفيذ الأعمال المدنية والإنشائية والكهروميكانيكية مرحلياً",
                "اختبارات الجودة والسلامة ومطابقة المواصفات القياسية",
                "إصدار شهادة إتمام البناء والتسليم النهائي للمشروع"
            ),
            relevantIndustriesEn = listOf("Real Estate", "Government", "Manufacturing", "Hospitality", "Logistics"),
            relevantIndustriesAr = listOf("العقارات", "القطاع الحكومي", "الصناعة", "الضيافة", "اللوجستيات"),
            ctaEn = "Discuss Your Project",
            ctaAr = "ناقش مشروعك الإنشائي",
            whatsappMessageEn = "Hello Al Tasheer Mena, I would like to discuss our Construction project in Saudi Arabia.",
            whatsappMessageAr = "مرحباً التأشير مينا، أود مناقشة مشروع المقاولات والإنشاءات التابع لنا في المملكة.",
            iconCategory = "construction"
        ),
        ServiceItem(
            id = "landscaping",
            titleEn = "Landscaping",
            titleAr = "تنسيق وتطوير المساحات الخضراء",
            taglineEn = "Sustainable Architecture, Softscape, Hardscape & Irrigation",
            taglineAr = "عمارة البيئة المستدامة، المسطحات الخضراء، وشبكات الري الذكية",
            descriptionEn = "Transform corporate campuses, hospitality destinations, and municipal developments into lush, sustainable environments aligned with the Saudi Green Initiative. We engineer softscapes, hardscapes, smart drip irrigation, and ongoing horticultural maintenance.",
            descriptionAr = "تطوير المجمعات المؤسسية والوجهات السياحية والمشاريع الحضرية ببيئات خضراء مستدامة تدعم مبادرة السعودية الخضراء. نتولى تصميم وتنفيذ المسطحات الخضراء والصلبة، وشبكات الري الذكية، وبرامج الصيانة الدورية.",
            keyBenefitsEn = listOf(
                "Saudi Green Initiative Aligned Sustainable Plant Selection",
                "Smart Water-saving Drip Irrigation Engineering",
                "Complete Softscaping (Turf, Palms, Native Flora, Ground Covers)",
                "Complete Hardscaping (Walkways, Paving, Water Features, Pergolas)",
                "Scheduled Commercial Grounds Maintenance & Plant Health Care"
            ),
            keyBenefitsAr = listOf(
                "اختيار نباتات بيئية مستدامة متوافقة مع مبادرة السعودية الخضراء",
                "تصميم شبكات ري ذكية مرشدة لاستهلاك المياه",
                "تنفيذ المساحات النباتية (الأشجار المحلية، النخيل، المسطحات الخضراء)",
                "أعمال الهاردسكيب (الممرات، النوافير، المظلات، والجلسات)",
                "عقود الصيانة الدورية الشاملة للمساحات الخضراء"
            ),
            whatWeProvideEn = listOf(
                "Landscape Architectural Master Planning & 3D Visualizations",
                "Commercial Softscape Installation with Drought-tolerant Species",
                "Hardscape Paving, Retaining Walls & Outdoor Lighting Systems",
                "Automated Weather-sensing Irrigation Control Systems",
                "Long-term Corporate Maintenance & Pruning Services"
            ),
            whatWeProvideAr = listOf(
                "التخطيط المعماري وتصاميم اللاندسكيب ثلاثية الأبعاد",
                "توريد وزراعة النباتات المقاومة للمناخ الصحراوي",
                "رصف الممرات والإنارة الخارجية والعناصر الجمالية",
                "أنظمة ري أوتوماتيكية متصلة بمستشعرات المناخ",
                "خدمات الصيانة الدورية والعناية الدائمة بالنباتات"
            ),
            processEn = listOf(
                "Soil, Water Quality & Solar Exposure Assessment",
                "Sustainable Landscape Design & Material Specification",
                "Earthmoving, Grading & Subsurface Drainage Installation",
                "Irrigation Infrastructure & Horticultural Planting",
                "Commissioning & Scheduled Preventative Maintenance"
            ),
            processAr = listOf(
                "فحص التربة وجودة المياه ودراسة العوامل المناخية",
                "إعداد التصاميم وتحديد المواد والنباتات المناسبة",
                "أعمال التسوية وتمديد شبكات الصرف والري التحتية",
                "تنفيذ أعمال الرصف والزراعة وشبكات الإنارة",
                "التشغيل والبدء في برامج الصيانة الدورية الوقائية"
            ),
            relevantIndustriesEn = listOf("Real Estate", "Hospitality", "Government", "Retail"),
            relevantIndustriesAr = listOf("العقارات", "الضيافة", "القطاع الحكومي", "التجزئة"),
            ctaEn = "Request Landscaping Consultation",
            ctaAr = "طلب استشارة أعمال اللاندسكيب",
            whatsappMessageEn = "Hello Al Tasheer Mena, I am interested in your Landscaping & Sustainable Architecture services.",
            whatsappMessageAr = "مرحباً التأشير مينا، أود استشارة حول خدمات اللاندسكيب وتنسيق المشاريع التابعة لنا.",
            iconCategory = "landscaping"
        )
    )

    fun getIndustries(): List<IndustryItem> = listOf(
        IndustryItem(
            id = "government",
            nameEn = "Government & Public Sector",
            nameAr = "القطاع الحكومي والمؤسسات العامة",
            descriptionEn = "Supporting municipal authorities, giga-project entities, and Vision 2030 initiatives with regulatory compliance, workforce solutions, and specialized contracting.",
            descriptionAr = "دعم الهيئات والجهات الحكومية والمشاريع الكبرى بتحقيق متطلبات الامتثال، والكوادر البشرية، وأعمال المقاولات واللاندسكيب المتخصصة.",
            relevantServicesEn = listOf("Governmental Compliance", "Manpower Solutions", "Construction", "Landscaping"),
            relevantServicesAr = listOf("الامتثال الحكومي", "حلول القوى العاملة", "المقاولات", "تنسيق المساحات الخضراء"),
            marketInsightsEn = "Deep familiarity with Saudi Government Tenders and Procurement Law (GTPL), Etimad platform bidding, and ministerial qualification standards.",
            marketInsightsAr = "خبرة واسعة في نظام المنافسات والمشتريات الحكومية ومنصة اعتماد واشتراطات التأهيل لدى الوزارات.",
            iconCategory = "government"
        ),
        IndustryItem(
            id = "real-estate",
            nameEn = "Real Estate & Infrastructure",
            nameAr = "العقارات والبنية التحتية",
            descriptionEn = "Partnering with master developers and property managers to develop commercial assets, deliver turnkey construction, and maintain sustainable landscapes.",
            descriptionAr = "التعاون مع المطورين العقاريين لإنجاز المنشآت التجارية، وإدارة أعمال البناء والمقاولات، وتطوير المساحات الخضراء المستدامة.",
            relevantServicesEn = listOf("Construction", "Landscaping", "Company Formation", "Financial Advisory"),
            relevantServicesAr = listOf("المقاولات والإنشاءات", "اللاندسكيب", "تأسيس الشركات", "الاستشارات المالية"),
            marketInsightsEn = "Navigating Balady municipal licensing, Wafi off-plan sales compliance, and civil defense certifications across Saudi urban developments.",
            marketInsightsAr = "إدارة تراخيص منصة بلدي، ومتطلبات برنامج وافي، واعتمادات السلامة بمشاريع التطوير العقاري الكبرى.",
            iconCategory = "real-estate"
        ),
        IndustryItem(
            id = "manufacturing",
            nameEn = "Manufacturing & Heavy Industry",
            nameAr = "الصناعة والإنتاج",
            descriptionEn = "Helping domestic and international manufacturers obtain industrial licenses (MODON), clear machinery customs, and source skilled technical labor.",
            descriptionAr = "مساعدة المصانع المحلية والدولية في استخراج التراخيص الصناعية (مدن)، والفسح الجمركي للمعدات وخطوط الإنتاج، وتوفير العمالة الفنية.",
            relevantServicesEn = listOf("Company Formation", "Logistics", "Manpower Solutions", "Governmental Compliance"),
            relevantServicesAr = listOf("تأسيس الشركات", "الخدمات اللوجستية", "حلول القوى العاملة", "الامتثال الحكومي"),
            marketInsightsEn = "Accelerating factory setup across MODON industrial cities and Royal Commission for Jubail and Yanbu (RCJY) industrial zones.",
            marketInsightsAr = "تسريع إنشاء المصانع في المدن الصناعية (مدن) ومناطق الهيئة الملكية للجبيل وينبع.",
            iconCategory = "manufacturing"
        ),
        IndustryItem(
            id = "hospitality",
            nameEn = "Hospitality, Tourism & HoReCa",
            nameAr = "الضيافة والسياحة والمطاعم",
            descriptionEn = "Empowering luxury hotels, resorts, and restaurant chains with Ministry of Tourism licenses, SFDA food compliance, and bilingual hospitality staff.",
            descriptionAr = "تمكين الفنادق والمنتجعات وسلاسل المطاعم العالمية من الحصول على تراخيص وزارة السياحة وهيئة الغذاء، وتوفير الكوادر المؤهلة.",
            relevantServicesEn = listOf("Food Division", "Manpower Solutions", "Company Formation", "Landscaping"),
            relevantServicesAr = listOf("قطاع الأغذية", "القوى العاملة", "تأسيس الشركات", "اللاندسكيب"),
            marketInsightsEn = "Aligned with Saudi Vision 2030 tourism targets to welcome 150 million annual visitors with world-class hospitality support.",
            marketInsightsAr = "مواكبة مستهدفات رؤية 2030 لقطاع السياحة والضيافة بأعلى المعايير العالمية.",
            iconCategory = "hospitality"
        ),
        IndustryItem(
            id = "healthcare",
            nameEn = "Healthcare & Pharmaceuticals",
            nameAr = "الرعاية الصحية والأدوية",
            descriptionEn = "Assisting medical providers, clinics, and pharmaceutical distributors with Ministry of Health (MOH) and SFDA medical registration, staffing, and compliance.",
            descriptionAr = "مساعدة المراكز الطبية والمستشفيات وشركات الأدوية بتراخيص وزارة الصحة وهيئة الغذاء والدواء وتوفير الكوادر الطبية والإدارية.",
            relevantServicesEn = listOf("Governmental Compliance", "Manpower Solutions", "Logistics", "Financial Advisory"),
            relevantServicesAr = listOf("الامتثال الحكومي", "القوى العاملة", "اللوجستيات", "الاستشارات المالية"),
            marketInsightsEn = "Ensuring end-to-end compliance with Saudi Commission for Health Specialties (SCFHS) credentialing and cold-chain medical freight.",
            marketInsightsAr = "ضمان الامتثال لاشتراطات الهيئة السعودية للتخصصات الصحية وسلاسل الإمداد الطبي المبرد.",
            iconCategory = "healthcare"
        ),
        IndustryItem(
            id = "logistics",
            nameEn = "Logistics & Supply Chain",
            nameAr = "الخدمات اللوجستية والنقل",
            descriptionEn = "Supporting third-party logistics (3PL) operators, freight forwarders, and distribution centers with customs clearance, warehousing, and fleet compliance.",
            descriptionAr = "دعم مشغلي الخدمات اللوجستية ومستودعات التوزيع بالتخليص الجمركي وتراخيص هيئة النقل وإدارة الأساطيل.",
            relevantServicesEn = listOf("Logistics", "Governmental Compliance", "Manpower Solutions", "Company Formation"),
            relevantServicesAr = listOf("الخدمات اللوجستية", "الامتثال الحكومي", "حلول القوى العاملة", "تأسيس الشركات"),
            marketInsightsEn = "Capitalizing on the National Transport and Logistics Strategy (NTLS) to position Saudi Arabia as a global logistical hub.",
            marketInsightsAr = "الاستفادة من الاستراتيجية الوطنية للنقل والخدمات اللوجستية لربط القارات الثلاث.",
            iconCategory = "logistics"
        ),
        IndustryItem(
            id = "retail",
            nameEn = "Retail & Consumer Goods",
            nameAr = "التجزئة والسلع الاستهلاكية",
            descriptionEn = "Facilitating multi-branch store rollouts, commercial registration, point-of-sale ZATCA e-invoicing compliance, and retail floor staffing.",
            descriptionAr = "تسهيل افتتاح الفروع التجارية للماركات العالمية، واستخراج التراخيص، وربط نقاط البيع بالفوترة الإلكترونية، وتوفير موظفي التجزئة.",
            relevantServicesEn = listOf("Company Formation", "Governmental Compliance", "Food Division", "Manpower Solutions"),
            relevantServicesAr = listOf("تأسيس الشركات", "الامتثال الحكومي", "قطاع الأغذية", "القوى العاملة"),
            marketInsightsEn = "Direct support for commercial leases, Balady commercial store permits, and Saudization quotas in retail sales.",
            marketInsightsAr = "دعم كامل لعقود الإيجار التجارية وتراخيص بلدي وتطبيق نسب التوطين في منافذ البيع.",
            iconCategory = "retail"
        ),
        IndustryItem(
            id = "technology",
            nameEn = "Technology & Digital Services",
            nameAr = "التقنية والخدمات الرقمية",
            descriptionEn = "Empowering global tech companies, SaaS platforms, and digital startups to establish Saudi regional headquarters (RHQ) and secure MISA licenses.",
            descriptionAr = "تمكين كبرى شركات التقنية والمنصات الرقمية من تأسيس مقراتها الإقليمية بالمملكة واستخراج تراخيص وزارة الاستثمار المتخصصة.",
            relevantServicesEn = listOf("Company Formation", "Financial Advisory", "Governmental Compliance", "Manpower Solutions"),
            relevantServicesAr = listOf("تأسيس الشركات", "الاستشارات المالية", "الامتثال الحكومي", "القوى العاملة"),
            marketInsightsEn = "Full advisory on Saudi Regional Headquarters (RHQ) incentive program, CITC communications permits, and National Cybersecurity Authority (NCA) compliance.",
            marketInsightsAr = "استشارات متخصصة لبرنامج المقرات الإقليمية (RHQ) وتراخيص هيئة الاتصالات والفضاء وهيئة الأمن السيبراني.",
            iconCategory = "technology"
        )
    )

    fun getHowItWorksSteps(): List<TimelineStep> = listOf(
        TimelineStep(
            stepNumber = 1,
            titleEn = "Consultation",
            titleAr = "الاستشارة ودراسة الجدوى",
            descriptionEn = "In-depth discovery session evaluating market fit, corporate entity structuring, capitalization requirements, and roadmap mapping.",
            descriptionAr = "جلسة تقييم شاملة لدراسة الملاءمة في السوق، وتحديد الهيكل القانوني الأنسب، وخارطة التكاليف والجدول الزمني.",
            highlightEn = "Market fit, structure & cost mapping",
            highlightAr = "تحديد الهيكل القانوني ومسار التكاليف"
        ),
        TimelineStep(
            stepNumber = 2,
            titleEn = "Registration",
            titleAr = "التسجيل والتأسيس الرسمي",
            descriptionEn = "Preparation and filing for MISA foreign investment license, Ministry of Commerce bylaws drafting, and Commercial Registration (CR).",
            descriptionAr = "إعداد وتقديم ملف ترخيص الاستثمار (MISA)، وصياغة وتوثيق عقود التأسيس، واستخراج السجل التجاري الرسمي.",
            highlightEn = "MISA investment license & CR",
            highlightAr = "رخصة الاستثمار الأجنبي والسجل التجاري"
        ),
        TimelineStep(
            stepNumber = 3,
            titleEn = "Licensing",
            titleAr = "التراخيص القطاعية والبلدية",
            descriptionEn = "Obtaining mandatory sector-specific operating licenses, industrial approvals, municipality permits (Balady), and civil defense.",
            descriptionAr = "استخراج تراخيص الأنشطة المتخصصة، والموافقات الصناعية، ورخص البلدية (بلدي)، وشهادات الدفاع المدني.",
            highlightEn = "Activity, factory & municipal permits",
            highlightAr = "رخص الأنشطة والبلدية والدفاع المدني"
        ),
        TimelineStep(
            stepNumber = 4,
            titleEn = "Compliance",
            titleAr = "الامتثال والتسجيل الحكومي",
            descriptionEn = "Activating national business portals: ZATCA tax & e-invoicing Phase 2, GOSI social insurance, Qiwa labor portal, and Muqeem.",
            descriptionAr = "تفعيل والربط مع المنصات الوطنية: هيئة الزكاة والضريبة (الفاتورة الإلكترونية)، التأمينات الاجتماعية، وقوى، ومقيم.",
            highlightEn = "Regulatory and operational compliance",
            highlightAr = "الامتثال الضريبي والتنظيمي الكامل"
        ),
        TimelineStep(
            stepNumber = 5,
            titleEn = "Operations",
            titleAr = "التشغيل والتوظيف والأنظمة",
            descriptionEn = "Activating corporate banking facilities, hiring key personnel, issuing employee IQAMAs, and leasing operational office facilities.",
            descriptionAr = "فتح وتفعيل الحسابات البنكية للمنشأة، وتوظيف الكفاءات وإصدار الإقامات، واستئجار المقرات وتجهيز الأنظمة.",
            highlightEn = "Banking, hiring, office & systems",
            highlightAr = "الحسابات البنكية، التوظيف، وتجهيز المقرات"
        ),
        TimelineStep(
            stepNumber = 6,
            titleEn = "Growth",
            titleAr = "التوسع والمناقصات والشراكات",
            descriptionEn = "Supporting long-term corporate growth through government procurement bids (Etimad), supply chain expansion, and strategic partnerships.",
            descriptionAr = "دعم نمو الأعمال المستمر من خلال الدخول في المنافسات والمناقصات الحكومية (اعتماد)، وتوسيع الشراكات وسلاسل الإمداد.",
            highlightEn = "Expansion, tenders & partnerships",
            highlightAr = "التوسع والشراكات والمناقصات الكبرى"
        )
    )

    fun getTestimonials(): List<TestimonialItem> = listOf(
        TestimonialItem(
            id = "t1",
            clientName = "Executive Leadership",
            clientRoleEn = "Managing Director, Logistics Division",
            clientRoleAr = "المدير العام، قطاع اللوجستيات",
            company = "RTL Rack-Tech Logistics",
            quoteEn = "Al Tasheer Mena demonstrated remarkable proficiency in orchestrating our Saudi logistics licensing and customs protocols. Their on-ground relationships saved us months of setup time.",
            quoteAr = "أظهر فريق التأشير مينا احترافية استثنائية في تنسيق تراخيصنا اللوجستية والإجراءات الجمركية بالمملكة. وفرت خبرتهم الميدانية أشهراً من وقت التأسيس."
        ),
        TestimonialItem(
            id = "t2",
            clientName = "Operations Director",
            clientRoleEn = "Head of Corporate Governance",
            clientRoleAr = "رئيس الحوكمة المؤسسية",
            company = "Initial Saudi Group",
            quoteEn = "Their team's mastery of Qiwa, Muqeem, and regulatory compliance is unmatched. They act not merely as a service provider, but as a reliable strategic extension of our enterprise.",
            quoteAr = "تمكن فريقهم من منصات قوى ومقيم والامتثال التنظيمي لا يُعلى عليه. هم ليسوا مجرد مزود خدمات، بل امتداد استراتيجي حقيقي لأعمالنا."
        ),
        TestimonialItem(
            id = "t3",
            clientName = "Regional Expansion Lead",
            clientRoleEn = "VP International Business Development",
            clientRoleAr = "نائب الرئيس لتطوير الأعمال الدولية",
            company = "Aletihad Business Conglomerate",
            quoteEn = "Establishing our corporate presence in Riyadh and Jeddah was seamless thanks to Al Tasheer Mena's MISA guidance and financial advisory. Truly premier Saudi corporate services.",
            quoteAr = "كان تأسيس حضورنا التجاري في الرياض وجدة في غاية السلاسة بفضل توجيهات التأشير مينا في تراخيص الاستثمار والاستشارات المالية. خدمات مؤسسية سعودية رائدة بحق."
        )
    )

    fun getTrustedClients(): List<String> = listOf(
        "Aletihad",
        "Initial Saudi Group",
        "Kudu",
        "RTL Rack-Tech Logistics",
        "Bahri",
        "Diriyah Biennale Foundation",
        "Nissan Petromin",
        "The Coffee Address"
    )

    fun getFaqs(): List<FaqItem> = listOf(
        FaqItem(
            id = "faq1",
            category = "Company Formation & Compliance",
            questionEn = "Can a foreign company own 100% of an enterprise in Saudi Arabia?",
            questionAr = "هل يحق للشركات الأجنبية التملك بنسبة 100% في المملكة العربية السعودية؟",
            answerEn = "Yes. Under the Ministry of Investment of Saudi Arabia (MISA) regulations, foreign investors can establish 100% foreign-owned limited liability companies (LLCs) across a wide range of commercial, industrial, and service activities.",
            answerAr = "نعم. تتيح لوائح وزارة الاستثمار بالمملكة (MISA) للمستثمرين الأجانب تأسيس شركات ذات مسؤولية محدودة بتملك أجنبي كامل بنسبة 100% في معظم الأنشطة التجارية والصناعية والخدمية."
        ),
        FaqItem(
            id = "faq2",
            category = "Company Formation & Compliance",
            questionEn = "How long does it typically take to obtain a MISA foreign investment license?",
            questionAr = "كم يستغرق استخراج رخصة الاستثمار الأجنبي من وزارة الاستثمار (MISA)؟",
            answerEn = "With all attested and apostilled documents in order, the MISA license can typically be issued within 3 to 7 working days, followed by commercial registration and chamber of commerce issuance.",
            answerAr = "في حال جاهزية الوثائق والتصديقات المطلوبة، يتم إصدار رخصة وزارة الاستثمار عادة خلال 3 إلى 7 أيام عمل، تليها إجراءات السجل التجاري والاشتراك بالغرفة التجارية."
        ),
        FaqItem(
            id = "faq3",
            category = "Governmental & Regulatory Compliance",
            questionEn = "What ongoing governmental platforms does Al Tasheer Mena manage for corporate clients?",
            questionAr = "ما هي المنصات الحكومية التي تتولى شركة التأشير مينا إدارتها لعملائها؟",
            answerEn = "We manage all mandatory portals including Qiwa (labor contracts), Muqeem (visas & IQAMAs), ZATCA (Zakat, tax, and Phase 2 e-invoicing), GOSI (social insurance), Balady (municipal permits), and Civil Defense (Salama).",
            answerAr = "نتولى إدارة كافة البوابات الإلزامية ومنها منصة قوى (عقود العمل)، مقيم (التأشيرات والإقامات)، هيئة الزكاة والضريبة والجمارك زاتكا (الفاتورة الإلكترونية)، التأمينات الاجتماعية، بلدي (الرخص البلدية)، وسلامة (الدفاع المدني)."
        ),
        FaqItem(
            id = "faq4",
            category = "Financial Advisory",
            questionEn = "What does your financial advisory entail for companies entering Saudi Arabia?",
            questionAr = "ماذا تشمل الاستشارات المالية للشركات الراغبة في دخول السوق السعودي؟",
            answerEn = "Our financial advisory covers corporate capitalization models, cash-flow forecasting, working capital restructuring, SIDF industrial funding applications, and aligning your financial systems with Saudi accounting standards.",
            answerAr = "تشمل استشاراتنا المالية تخطيط التدفقات النقدية، وهيكلة رأس المال العامل، وإعداد ملفات التمويل الصناعي لصندوق التنمية الصناعية، ومطابقة النظم المالية لمعايير المحاسبة المعتمدة بالمملكة."
        ),
        FaqItem(
            id = "faq5",
            category = "Manpower Solutions",
            questionEn = "How does Al Tasheer Mena assist with Saudization (Nitaqat) requirements?",
            questionAr = "كيف تساعد التأشير مينا في تلبية متطلبات نسب التوطين (برنامج نطاقات)؟",
            answerEn = "We formulate strategic Saudization models balancing local qualified talent placement with expatriate visa quotas, ensuring your company remains in the high Green or Platinum Nitaqat tiers for unrestricted operations.",
            answerAr = "نضع نماذج متوازنة لاستقطاب الكفاءات الوطنية المؤهلة بالتوازي مع إدارة رصيد تأشيرات العمل، بما يضمن بقاء منشأتكم في النطاق الأخضر المرتفع أو البلاتيني دون أي قيود تشغيلية."
        ),
        FaqItem(
            id = "faq6",
            category = "Food Division",
            questionEn = "What support do you provide for importing food products through Saudi Food & Drug Authority (SFDA)?",
            questionAr = "ما هو الدعم المقدم لاستيراد وتسجيل المنتجات بهيئة الغذاء والدواء (SFDA)؟",
            answerEn = "We handle ingredient conformity reviews, product barcode classification, SFDA FADSO portal registration, laboratory inspection clearances, and customs release at Saudi sea and air entry ports.",
            answerAr = "نتولى مطابقة مكونات وبطاقة المنتج للمواصفات القياسية، وتسجيل الباركود بنظام الهيئة، ومتابعة الفحوصات المخبرية، وإتمام الفسح الجمركي السريع بكافة منافذ المملكة."
        ),
        FaqItem(
            id = "faq7",
            category = "Logistics",
            questionEn = "Can Al Tasheer Mena handle customs clearance at all major Saudi ports?",
            questionAr = "هل تغطي خدمات التخليص الجمركي كافة موانئ ومنافذ المملكة الرئيسية؟",
            answerEn = "Yes. Through the Fasah electronic customs system, we handle cargo clearance at Jeddah Islamic Port, King Abdulaziz Port in Dammam, King Abdullah Port, King Khalid International Airport (RUH), and major overland border posts.",
            answerAr = "نعم، عبر نظام فسح الإلكتروني نتولى التخليص في ميناء جدة الإسلامي، ميناء الملك عبدالعزيز بالدمام، ميناء الملك عبدالله برابغ، مطار الملك خالد بالرياض، وكافة المنافذ البرية الرئيسية."
        ),
        FaqItem(
            id = "faq8",
            category = "Construction",
            questionEn = "What construction services do you offer for commercial and industrial clients?",
            questionAr = "ما هي خدمات المقاولات والإنشاءات المقدمة للعملاء التجاريين والصناعيين؟",
            answerEn = "We offer turnkey commercial contracting, warehouse and logistics facility erection, interior corporate fit-outs, MEP engineering, and obtaining municipal building completion certificates.",
            answerAr = "نقدم أعمال المقاولات العامة المتكاملة، وتشييد المستودعات والمرافق اللوجستية، والتشطيبات المعمارية للمقرات، والأعمال الكهروميكانيكية، وإصدار شهادات إتمام البناء الرسمية."
        ),
        FaqItem(
            id = "faq9",
            category = "Landscaping",
            questionEn = "How does your landscaping division adhere to sustainability and water efficiency?",
            questionAr = "كيف يلتزم قطاع اللاندسكيب بمعايير الاستدامة وكفاءة استهلاك المياه؟",
            answerEn = "We integrate native drought-resistant flora, automate weather-sensing drip irrigation, and implement sustainable soil conditioning strictly aligned with the goals of the Saudi Green Initiative.",
            answerAr = "نعتمد زراعة الأشجار المحلية المقاومة للمناخ الصحراوي، ونطبق شبكات الري الذكية بالتقطير المرشدة للمياه، بما يتوافق بدقة مع مستهدفات مبادرة السعودية الخضراء."
        )
    )
}
