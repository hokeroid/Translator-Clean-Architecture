package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

interface Interactor {
    fun saveDictionary(words: List<Word>)
    fun loadDictionary(): List<Word>
    fun getTranslation(originalText: String, targetLanguage: String): Single<Word>
}