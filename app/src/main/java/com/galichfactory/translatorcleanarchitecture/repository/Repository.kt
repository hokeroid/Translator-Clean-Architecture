package com.galichfactory.translatorcleanarchitecture.repository

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

interface Repository {
    fun getWords(context: Context) : List<Word> //TODO -Context +Dagger

    fun setWords(context: Context, words: List<Word>) //TODO -Context +Dagger

    fun getTranslation(originalText: String, targetLanguage: String) : Single<Word>
}