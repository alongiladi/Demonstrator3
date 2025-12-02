package com.example.demonstrator3.data
import androidx.room.Entity
import androidx.room.PrimaryKey


//ייצוג של הטבלה בדיבי
@Entity(tableName = "shopping_items")
data class ShoppingItem(
    // ה-ID יגדל אוטומטית (1, 2, 3...)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val quantity: Int
)