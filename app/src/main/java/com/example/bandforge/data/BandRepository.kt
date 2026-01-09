package com.example.bandforge.data

import kotlinx.coroutines.flow.Flow

class BandRepository(private val bandDao: BandDao, private val geminiService: GeminiService) {

    val allItems: Flow<List<BandItem>> = bandDao.getAllItems()

    fun getItem(id: Int): Flow<BandItem?> {
        return bandDao.getItem(id)
    }

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