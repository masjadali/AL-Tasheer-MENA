package com.example.util

import com.example.data.model.AppLanguage

object Localization {

    fun navHome(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "الرئيسية" else "Home"
    fun navServices(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "الخدمات" else "Services"
    fun navIndustries(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "القطاعات" else "Industries"
    fun navAbout(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "عن الشركة" else "About"
    fun navContact(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "اتصل بنا" else "Contact"

    fun getConsultation(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "طلب استشارة" else "Get a Consultation"
    fun exploreServices(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "استكشف الخدمات" else "Explore Services"
    fun chatOnWhatsApp(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "محادثة عبر واتساب" else "Chat on WhatsApp"
    fun callNow(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "اتصال هاتفياً" else "Call Directly"
    fun emailUs(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "إرسال بريد إلكتروني" else "Email Us"
    fun openInMaps(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "فتح في خرائط جوجل" else "Open in Google Maps"

    fun heroHeadline(lang: AppLanguage) = if (lang == AppLanguage.ARABIC)
        "شريكك المتكامل. لكافة القدرات. التي تحتاجها في المملكة."
    else
        "One Partner. Every Capability. You Need In Saudi Arabia."

    fun heroSupporting(lang: AppLanguage) = if (lang == AppLanguage.ARABIC)
        "من تأسيس الشركات وحلول القوى العاملة إلى الاستشارات المالية والامتثال القانوني والتموين واللوجستيات والمقاولات، نساعد الشركات على التأسيس والتشغيل والنمو في كافة أنحاء المملكة."
    else
        "From company formation and manpower solutions to legal, financial advisory, food supplies, logistics and construction, we help businesses establish, operate, and grow across the Kingdom."

    fun whyChooseUsTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "لماذا تختار التأشير مينا" else "Why Choose Al Tasheer Mena"
    fun howItWorksTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "كيف نعمل (مسار العمل)" else "How It Works"
    fun businessDivisionsTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "قطاعات أعمالنا المتخصصة" else "Our Business Divisions"
    fun industriesTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "القطاعات التي نخدمها" else "Industries We Serve"
    fun trustedLeadersTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "موثوق به من قِبل قادة الأعمال" else "Trusted By Leaders & Registered With"
    fun testimonialsTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "آراء وتجارب الشركاء" else "Client Testimonials"
    fun faqTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "الأسئلة الشائعة" else "Frequently Asked Questions"

    fun aboutHeroTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC)
        "تمكين ونمو الأعمال في كافة أنحاء المملكة"
    else
        "Empowering Businesses Across The Kingdom"

    fun aboutOverview(lang: AppLanguage) = if (lang == AppLanguage.ARABIC)
        "تأسست التأشير مينا في عام 1984، وتعتبر شريكاً استراتيجياً للمستثمرين الأجانب ورواد الأعمال والشركات الكبرى في المملكة العربية السعودية. بفضل خبرتنا العميقة في الأنظمة واللوائح السعودية ورؤية 2030، نقدم حلولاً تنفيذية شاملة تضمن سرعة الامتثال وتحقيق مستهدفات النمو."
    else
        "Founded in 1984, Al Tasheer Mena stands as a premier advisory and operational powerhouse guiding foreign investors, entrepreneurs, and global enterprises through Saudi Arabia's dynamic regulatory landscape. We transform strategic market opportunities into compliant, scalable, and enduring corporate realities aligned with Vision 2030."

    fun formFullName(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "الاسم بالكامل *" else "Full Name *"
    fun formCompanyName(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "اسم الشركة / المؤسسة *" else "Company Name *"
    fun formEmail(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "البريد الإلكتروني *" else "Email Address *"
    fun formPhone(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "رقم الهاتف / الجوال *" else "Phone Number *"
    fun formCountry(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "الدولة / المقر" else "Country"
    fun formService(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "الخدمة المطلوبة *" else "Service Required *"
    fun formIndustry(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "القطاع التجاري *" else "Industry *"
    fun formMessage(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "تفاصيل الاستفسار أو المشروع" else "Inquiry Details / Message"
    fun formContactMethod(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "طريقة التواصل المفضلة" else "Preferred Contact Method"
    fun formSubmit(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "إرسال طلب الاستشارة" else "Submit Consultation Request"
    fun formSuccessMsg(lang: AppLanguage) = if (lang == AppLanguage.ARABIC)
        "شكراً لتواصلكم. سيقوم فريقنا الاستشاري بالتواصل معكم قريباً."
    else
        "Thank you. Our team will contact you shortly."
    fun formValidationErr(lang: AppLanguage) = if (lang == AppLanguage.ARABIC)
        "يرجى ملء جميع الحقول المطلوبة بشكل صحيح."
    else
        "Please fill in all required fields properly."

    fun officeLocationTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "المقر الرئيسي" else "Corporate Office"
    fun privacyPolicyTitle(lang: AppLanguage) = if (lang == AppLanguage.ARABIC) "سياسة الخصوصية" else "Privacy Policy"
}
