package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface WordDao {
    @Insert
    fun insert(word: DbWord)

    @Update
    fun update(word: DbWord)

    @Delete
    fun delete(word: DbWord)
}