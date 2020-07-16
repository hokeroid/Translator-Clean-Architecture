package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT * FROM DbWord")
    fun getAll(): List<DbWordWithTranslations>

    @Query("SELECT * FROM DbWord WHERE word = :wordName")
    fun getById(wordName: String): DbWordWithTranslations

    @Insert
    fun insert(word: DbWord)

    @Insert
    fun insert(translation: DbTranslation)

    @Update
    fun update(word: DbWord)

    @Delete
    fun delete(word: DbWord)
}