package com.example.demonstrator3.data


import kotlinx.coroutines.flow.Flow

// מקבל אליו את הדאו בקונסטרקטור
// זה נקרא "הזרקת תלויות (Dependency Injection)
class ShoppingRepository(private val shoppingDao: ShoppingDao) {

    //משתנה שמחזיק בתוכו את כל הפריטים תוך שיקוף שלהם בזמן אמת מול הדיבי (פלואו)
    val allItems: Flow<List<ShoppingItem>> = shoppingDao.getAllItems()

    // פונקציה להוספה - פשוט מעבירה את הבקשה ל-DAO
    suspend fun insert(item: ShoppingItem) {
        shoppingDao.insert(item)
    }

    // פונקציה למחיקה - כנ"ל
    suspend fun delete(item: ShoppingItem) {
        shoppingDao.delete(item)

        suspend fun update(item: ShoppingItem) {
            shoppingDao.update(item)
        }
    }
}