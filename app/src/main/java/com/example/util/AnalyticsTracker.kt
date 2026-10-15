package com.example.util

import android.util.Log

object AnalyticsTracker {
    private const val TAG = "TasheerAnalytics"

    /**
     * Firebase Analytics-ready event dispatcher.
     * Logs structured events for Google Analytics / BigQuery ingestion.
     */
    fun logEvent(eventName: String, params: Map<String, Any?> = emptyMap()) {
        val paramString = params.entries.joinToString(", ") { "${it.key}=${it.value}" }
        Log.d(TAG, "Event: $eventName | Params: [$paramString]")
    }
}
