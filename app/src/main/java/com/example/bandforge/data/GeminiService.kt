package com.example.bandforge.data

import com.example.bandforge.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel

class GeminiService {

    private val generativeModel = GenerativeModel(

        modelName = "gemini-pro",

        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun generateBandName(prompt: String): String {
        return try {
            val response = generativeModel.generateContent(prompt)
            response.text ?: "Error: Could not generate name."
        } catch (e: Exception) {
            // החזרת הודעת שגיאה במקרה של כשל בתקשורת
            e.localizedMessage ?: "An unknown error occurred."
        }
    }
}