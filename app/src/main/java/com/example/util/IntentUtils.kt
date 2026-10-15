package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.net.URLEncoder

object IntentUtils {

    const val OFFICIAL_PHONE = "+966560736602"
    const val OFFICIAL_PHONE_DISPLAY = "+966 56 073 6602"
    const val OFFICIAL_EMAIL = "enquiry@tasheermena.com"
    const val OFFICIAL_WEBSITE = "https://www.tasheermena.com/"
    const val OFFICE_ADDRESS = "Office No.1, Al Oudaba Street, Near Naseem Polyclinic, Jeddah, Saudi Arabia"
    const val OFFICE_MAP_QUERY = "Al Oudaba Street, Jeddah, Saudi Arabia"

    fun openWhatsApp(context: Context, message: String, phone: String = OFFICIAL_PHONE) {
        AnalyticsTracker.logEvent("whatsapp_click", mapOf("phone" to phone))
        val cleanPhone = phone.replace("+", "").replace(" ", "").replace("-", "")
        val encodedMessage = try {
            URLEncoder.encode(message, "UTF-8")
        } catch (e: Exception) {
            message
        }

        try {
            val appIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("whatsapp://send?phone=$cleanPhone&text=$encodedMessage")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(appIntent)
        } catch (e: Exception) {
            // Fallback to web WhatsApp URL if app is not installed
            try {
                val webIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://api.whatsapp.com/send?phone=$cleanPhone&text=$encodedMessage")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(webIntent)
            } catch (err: Exception) {
                Toast.makeText(context, "Unable to launch WhatsApp or browser.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun dialPhone(context: Context, phone: String = OFFICIAL_PHONE) {
        AnalyticsTracker.logEvent("phone_click", mapOf("phone" to phone))
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to launch dialer.", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendEmail(
        context: Context,
        subject: String = "Business Consultation Request - Al Tasheer Mena",
        body: String = "",
        toEmail: String = OFFICIAL_EMAIL
    ) {
        AnalyticsTracker.logEvent("email_click", mapOf("email" to toEmail))
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$toEmail")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                if (body.isNotEmpty()) {
                    putExtra(Intent.EXTRA_TEXT, body)
                }
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to open email client.", Toast.LENGTH_SHORT).show()
        }
    }

    fun openOfficeLocation(context: Context) {
        AnalyticsTracker.logEvent("map_click", mapOf("address" to OFFICE_ADDRESS))
        try {
            val encodedQuery = Uri.encode(OFFICE_MAP_QUERY)
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$encodedQuery")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            // Browser fallback for Google Maps
            try {
                val webMapIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(OFFICE_MAP_QUERY)}")
                ).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(webMapIntent)
            } catch (err: Exception) {
                Toast.makeText(context, "Unable to open Google Maps.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openWebsite(context: Context, url: String = OFFICIAL_WEBSITE) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to open browser.", Toast.LENGTH_SHORT).show()
        }
    }
}
