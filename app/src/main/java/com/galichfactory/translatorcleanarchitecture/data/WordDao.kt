package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface WordDao {
    @Query("SELECT * FROM DbWord")
    fun getAll(): Flowable<List<DbWord>>

    @Insert
    fun insert(word: DbWord): Completable

    @Update
    fun update(word: DbWord)

    @Delete
    fun delete(word: DbWord)
}