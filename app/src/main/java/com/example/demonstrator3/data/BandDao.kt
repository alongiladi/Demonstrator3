package com.example.demonstrator3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BandDao {
    @Query("SELECT * FROM band_items")
    fun getAllItems(): Flow<List<BandItem>>

    @Insert
    suspend fun insert(item: BandItem)

    @Delete
    suspend fun delete(item: BandItem)

    @Update
    suspend fun update(item: BandItem)
}
