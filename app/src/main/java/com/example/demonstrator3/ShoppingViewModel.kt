package com.example.demonstrator3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.demonstrator3.data.ShoppingItem
import com.example.demonstrator3.data.ShoppingRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

// מקבל אליו את הרפוסטורי בקונסטרקטור, כמו שהרפוסטורי קיבל אליו את הדאו
// זה נקרא "הזרקת תלויות (Dependency Injection)
class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    // 1. חשיפת המידע למסך
    // אני פשוט מעביר את הפלואו שקיבלתי מהרפוסטורי הלאה ליו-איי (הממשק החזותי) שיקשיב לזה ויתעדכן אוטומטית
    val allItems: Flow<List<ShoppingItem>> = repository.allItems

    // 2. פעולות (Logic)
    // הוספת פריט
    fun addItem(name: String, quantity: String) {
        // המרה למספר (עם הגנה מקריסה אם זה לא מספר)
        val quantityInt = quantity.toIntOrNull() ?: 1

        val newItem = ShoppingItem(name = name, quantity = quantityInt)

        // השקה לקורוטינה (Coroutine)
        //אינסרט זו פונקציה א-סינכורנית - סספנד - אז אנחנו חייבים להצהיר עליה בתוך קורוטינה
        viewModelScope.launch {
            repository.insert(newItem)
        }
    }

    // מחיקת פריט
    fun deleteItem(item: ShoppingItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

  // יצירת חיבור ראשי של האפליקציה שלי לדאטאבייס
    //יצירת אובייקט פקטורי שיחבר אותנו לרפוזטורי היחיד באפליקציה - ואנחנו הרי רוצים שיהיה רק רפוסטורי אחד
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // אנחנו משיגים את ה-Application שיצרנו קודם
                val application = (extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShoppingApplication)
                // ומעבירים ל-ViewModel את ה-Repository המוכן
                return ShoppingViewModel(application.repository) as T
            }
        }
    }
}