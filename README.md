# Al Tasheer Mena — Android Mobile Application

Official Android Mobile Application for **Al Tasheer Mena** (https://www.tasheermena.com/).

A premium B2B corporate and business services platform designed for international investors, Saudi enterprises, multinational corporations, and SMEs establishing and scaling operations across the Kingdom of Saudi Arabia.

---

## 🏛️ Business Overview & Value Proposition

**Al Tasheer Mena** has been operating since **1984**, delivering **500+ corporate projects** with a **98% client retention rate** across Saudi Arabia.

> **Headline:** *"One Partner. Every Capability. You Need In Saudi Arabia."*
> **Mission:** *"From company formation and manpower solutions to legal, financial advisory, food supplies, logistics and construction, we help businesses establish, operate, and grow across the Kingdom."*

---

## 📱 Key Features & Modules

1. **Executive Home Screen**:
   - High-impact corporate hero section with Riyadh/Jeddah skyline architecture.
   - Core statistics bar: Founded 1984, 500+ Projects Delivered, 98% Client Retention, 15+ Years Combined Expertise.
   - 8 Business Divisions overview.
   - **Why Choose Al Tasheer Mena**: 100% regulatory compliance, end-to-end capabilities, and Saudi Vision 2030 alignment.
   - **How It Works**: 6-step interactive roadmap (Consultation ➔ Registration ➔ Licensing ➔ Compliance ➔ Operations ➔ Growth).
   - Strategic Industries grid.
   - **Trusted By Leaders**: Client associations including *Aletihad, Initial Saudi Group, Kudu, RTL Rack-Tech Logistics, Bahri, Diriyah Biennale Foundation, Nissan Petromin, The Coffee Address*.
   - Testimonial carousel and expandable FAQ accordion.

2. **8 Core Business Divisions**:
   - **Company Formation & Related Services**: MISA investment licensing, Commercial Registration (CR), 100% foreign ownership advisory, corporate bylaws notarization.
   - **Manpower Solutions**: Block visas, executive search, outsourced workforce, Qiwa contracts, Saudization (Nitaqat) compliance.
   - **Financial Advisory**: Working capital, cash-flow modeling, feasibility studies, SIDF financing support, corporate governance.
   - **Governmental & Regulatory Compliance**: Mastery of Qiwa, Muqeem, ZATCA e-invoicing Phase 2, GOSI, and Balady municipality permits.
   - **Food Division**: SFDA registration, Halal compliance, HoReCa distribution, cold chain logistics.
   - **Logistics**: Port customs clearance via Fasah (Jeddah Islamic Port, Dammam, King Abdullah Port, airports, land borders).
   - **Construction**: Turnkey commercial contracting, MEP engineering, industrial warehouses, Balady building completion.
   - **Landscaping**: Sustainable landscape architecture, Saudi Green Initiative alignment, smart drip irrigation, softscape & hardscape.

3. **Lead Generation System**:
   - 9-field consultation request form: Full Name, Company, Email, Phone, Country, Service Required, Industry, Message, and Preferred Contact Method.
   - Input validation and client feedback.
   - Persistent on-device storage via **Room Database** (`LeadEntity`, `LeadDao`).
   - Unique reference ID generation (e.g., `ATM-XXXXXX`).
   - Seamless 1-tap WhatsApp dispatch with prefilled reference details.
   - CRM-ready data architecture designed for future cloud sync (Firebase/CRM/Sheets).

4. **Multi-Channel Contact Integration**:
   - **WhatsApp**: Official line `+966 56 073 6602` with dynamic, service-tailored prefilled messages.
   - **Phone**: Native dialer intent (`tel:+966560736602`).
   - **Email**: Native email client intent (`mailto:enquiry@tasheermena.com`).
   - **Location**: Headquarters at *Office No.1, Al Oudaba Street, Near Naseem Polyclinic, Jeddah, Saudi Arabia* with Google Maps navigation intent.

5. **Bilingual Localization (English & Arabic)**:
   - Dynamic in-app toggle: `English | العربية`.
   - Complete Right-to-Left (RTL) layout switching via Compose `LocalLayoutDirection`.
   - Culturally and legally accurate Arabic corporate terminology.

6. **Google Play Store & Policy Compliance**:
   - Zero invasive permissions requested (no SMS, Contacts, Call Logs, or Storage permissions).
   - Dedicated Privacy Policy screen adhering to Saudi Personal Data Protection Law (PDPL) and Google Play Developer Program policies.
   - Adaptive launcher icons across all standard mipmap densities (`mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`).

---

## 🛠️ Technology Stack

- **Language:** Kotlin 2.2+
- **UI Framework:** Jetpack Compose (Material Design 3)
- **Architecture:** Clean Architecture / MVVM (Model-View-ViewModel)
- **Local Persistence:** AndroidX Room Database & Coroutines Flow
- **Navigation:** State-driven composable navigation with Predictive Back support (`BackHandler`)
- **Theme:** Executive corporate navy (`#0D1B2A`), champagne gold (`#C5A059`), and Saudi emerald green (`#006C35`)
- **Testing:** JUnit 4 & Robolectric

---

## 🚀 Build & Compilation

To compile and verify the Android application:

```bash
# Compile debug APK
gradle assembleDebug

# Run unit and Robolectric tests
gradle :app:testDebugUnitTest
```
