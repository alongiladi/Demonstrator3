package com.example.demonstrator3.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


//רשימת הפונקציות שאנחנו יכולים לעשות מול הדאטאבייס
@Dao
interface ShoppingDao {
    @Query("SELECT * FROM shopping_items")
    fun getAllItems(): Flow<List<ShoppingItem>>

    @Insert
    suspend fun insert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Update
    suspend fun update(item: ShoppingItem)

}