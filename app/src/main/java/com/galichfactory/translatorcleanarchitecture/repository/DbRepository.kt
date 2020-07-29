package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Completable
import io.reactivex.Flowable

interface DbRepository {
    fun getWords(): Flowable<List<Word>>

    fun insertWord(word: Word): Completable
}