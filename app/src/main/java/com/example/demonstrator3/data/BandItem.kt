package com.example.demonstrator3.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "band_items")
data class BandItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val genre: String,
    val notes: String
)
