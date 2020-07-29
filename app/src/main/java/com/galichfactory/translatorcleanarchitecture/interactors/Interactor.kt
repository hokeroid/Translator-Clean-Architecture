package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Flowable
import io.reactivex.Single

interface Interactor {
    fun getHistoryObservable(): Flowable<List<Word>>
    fun getTranslation(originalText: String, targetLanguage: String): Single<Word>
}