package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Flowable

interface Interactor {
    fun getHistoryObservable(): Flowable<List<Word>>
    fun getTranslation(originalText: String, targetLanguage: String)
}