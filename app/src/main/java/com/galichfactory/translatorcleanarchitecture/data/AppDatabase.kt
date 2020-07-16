package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DbWord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}

