package com.example.demonstrator3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BandItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bandDao(): BandDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    //.fallbackToDestructiveMigration() // אם משנים את גרסת ה-DB, זה ימחק ויבנה מחדש
                    .build()
                    .also { Instance = it }
            }
        }
    }
}