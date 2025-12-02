package com.example.demonstrator3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//הכרזה על הדאטאבייס ועל האנטיסי שיהיו קיימות בתוכו
//זו מחלקה אבסטרקטית כי אין  לנו כאן את הלוגיקה של כל הכלים שיעבדו עם הדיבי, כלומר משאירים מקום לכלים חיצוניים
@Database(entities = [ShoppingItem::class], version = 1, exportSchema = false) // [cite: 342]
abstract class ShoppingDatabase : RoomDatabase() {


    abstract fun shoppingDao(): ShoppingDao // [cite: 345]

    companion object {
        @Volatile
        private var Instance: ShoppingDatabase? = null

        fun getDatabase(context: Context): ShoppingDatabase {
            // אם המופע כבר קיים - תחזיר אותו.
            // אם לא - צור אותו, שמור אותו, ותחזיר אותו.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ShoppingDatabase::class.java,
                    "shopping_database" // שם הקובץ הפיזי בטלפון
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}