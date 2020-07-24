package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepository
import com.galichfactory.translatorcleanarchitecture.repository.DbRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val dbRepository: DbRepository,
    private val apiRepository: ApiRepository
) : Interactor {

    override fun getHistoryObservable(): Flowable<List<Word>> {
        return dbRepository.getWords()
    }

    override fun getTranslation(originalText: String, targetLanguage: String) {
        apiRepository.getTranslation(originalText, targetLanguage)
            .subscribeOn(Schedulers.io())
            .subscribe({ word ->
                dbRepository.insertWord(word)
            })
    }
}