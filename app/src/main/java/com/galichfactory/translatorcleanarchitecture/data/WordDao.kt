package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT * FROM DbWord")
    fun getAll(): List<DbWord>

    @Insert
    fun insert(word: DbWord)

    @Update
    fun update(word: DbWord)

    @Delete
    fun delete(word: DbWord)
}