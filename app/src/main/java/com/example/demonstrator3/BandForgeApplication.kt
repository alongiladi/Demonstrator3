package com.example.demonstrator3

import android.app.Application
import com.example.demonstrator3.data.AppDatabase
import com.example.demonstrator3.data.BandRepository
import com.example.demonstrator3.data.GeminiService

class BandForgeApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val geminiService by lazy { GeminiService() }

    val bandRepository by lazy { BandRepository(database.bandDao(), geminiService) }
}