package com.example.bandforge.data

import com.google.firebase.vertexai.FirebaseVertexAI

class GeminiService {

    private val generativeModel = FirebaseVertexAI.getInstance()
        .generativeModel("gemini-pro")

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