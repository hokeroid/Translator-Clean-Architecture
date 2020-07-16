package com.galichfactory.translatorcleanarchitecture.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DbWord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object Factory {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
        }
    }
}

