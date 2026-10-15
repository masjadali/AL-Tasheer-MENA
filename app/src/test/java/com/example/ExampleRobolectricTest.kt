package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppDatabase
import com.example.data.repository.TasheerRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    private lateinit var db: AppDatabase
    private lateinit var repository: TasheerRepository

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repository = TasheerRepository(db.leadDao())
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Al Tasheer Mena", appName)
    }

    @Test
    fun `verify all 8 corporate services loaded`() {
        val services = repository.getServices()
        assertEquals(8, services.size)
        assertTrue(services.any { it.id == "company-formation" })
        assertTrue(services.any { it.id == "manpower-solutions" })
        assertTrue(services.any { it.id == "financial-advisory" })
        assertTrue(services.any { it.id == "governmental-compliance" })
        assertTrue(services.any { it.id == "food-division" })
        assertTrue(services.any { it.id == "logistics" })
        assertTrue(services.any { it.id == "construction" })
        assertTrue(services.any { it.id == "landscaping" })
    }

    @Test
    fun `verify lead submission inserts into database`() = runBlocking {
        val lead = repository.submitLead(
            fullName = "Tariq Al-Mansoor",
            companyName = "Apex Logistics International",
            email = "tariq@apexlogistics.com",
            phone = "+966551234567",
            country = "Saudi Arabia",
            serviceRequired = "Logistics",
            industry = "Logistics",
            message = "Need customs clearance for 20 containers at Jeddah Islamic Port.",
            preferredContactMethod = "WhatsApp"
        )

        assertNotNull(lead.leadReference)
        assertTrue(lead.leadReference.startsWith("ATM-"))
        assertEquals("New", lead.status)
        assertEquals(1, db.leadDao().getLeadCount())
    }
}
