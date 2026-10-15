package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consultation_leads")
data class LeadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val leadReference: String,
    val fullName: String,
    val companyName: String,
    val email: String,
    val phone: String,
    val country: String,
    val serviceRequired: String,
    val industry: String,
    val message: String,
    val preferredContactMethod: String,
    val status: String = "New", // New, Contacted, Qualified, Proposal Sent, Converted
    val createdAt: Long = System.currentTimeMillis()
)
