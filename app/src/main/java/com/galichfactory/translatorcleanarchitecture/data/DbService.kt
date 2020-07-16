package com.galichfactory.translatorcleanarchitecture.data

import android.content.Context
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteOpenHelper

class DbService : AppDatabase() {
    override fun wordDao(): WordDao {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    companion object Factory {
        fun create(context: Context) {
            Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
        }
    }
}