package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

interface Interactor {
    fun saveHistory(words: List<Word>)
    fun loadHistorySingle(): Single<List<Word>>
    fun getTranslation(originalText: String, targetLanguage: String): Single<List<Word>>
}