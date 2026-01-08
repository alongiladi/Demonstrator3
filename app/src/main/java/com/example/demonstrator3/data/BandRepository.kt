package com.example.demonstrator3.data

import kotlinx.coroutines.flow.Flow

class BandRepository(private val bandDao: BandDao, private val geminiService: GeminiService) {

    val allItems: Flow<List<BandItem>> = bandDao.getAllItems()

    suspend fun insert(item: BandItem) {
        bandDao.insert(item)
    }

    suspend fun delete(item: BandItem) {
        bandDao.delete(item)
    }

    suspend fun update(item: BandItem) {
        bandDao.update(item)
    }

    suspend fun generateBandName(prompt: String): String {
        return geminiService.generateBandName(prompt)
    }
}