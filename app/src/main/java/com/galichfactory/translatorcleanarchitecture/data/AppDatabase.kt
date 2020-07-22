package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.galichfactory.translatorcleanarchitecture.di.DaggerContextComponent

@Database(entities = [DbWord::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object Factory {
        fun create(): AppDatabase {
            val context = DaggerContextComponent.create().getContext()
            return Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}

