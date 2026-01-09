package com.example.bandforge

import android.app.Application
import com.example.bandforge.data.AppDatabase
import com.example.bandforge.data.BandRepository
import com.example.bandforge.data.GeminiService

class BandForgeApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val geminiService by lazy { GeminiService() }

    val bandRepository by lazy { BandRepository(database.bandDao(), geminiService) }
}