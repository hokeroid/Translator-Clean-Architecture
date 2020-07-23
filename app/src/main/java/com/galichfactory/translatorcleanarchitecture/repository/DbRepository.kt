package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

interface DbRepository {
    fun getWords(): Single<List<Word>> //TODO -Context +Dagger

    fun setWords(words: List<Word>) //TODO -Context +Dagger

    fun insertWord(word: Word)
}