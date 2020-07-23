package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.*
import io.reactivex.Single

@Dao
interface WordDao {
    @Query("SELECT * FROM DbWord")
    fun getAll(): Single<List<DbWord>>

    @Insert
    fun insert(word: DbWord)

    @Update
    fun update(word: DbWord)

    @Delete
    fun delete(word: DbWord)
}