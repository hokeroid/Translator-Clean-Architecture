package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

interface Repository {
    fun getWords() : List<Word> //TODO -Context +Dagger

    fun setWords(words: List<Word>) //TODO -Context +Dagger

    fun getTranslation(originalText: String, targetLanguage: String) : Single<Word>
}