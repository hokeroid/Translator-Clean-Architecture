package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepository
import com.galichfactory.translatorcleanarchitecture.repository.DbRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val dbRepository: DbRepository,
    private val apiRepository: ApiRepository
) : Interactor {
    override fun saveHistory(words: List<Word>) {
        dbRepository.setWords(words)
    }

    override fun loadHistorySingle(): Single<List<Word>> {
        return dbRepository.getWords()
    }

    override fun getTranslation(originalText: String, targetLanguage: String): Single<List<Word>> {
        return apiRepository.getTranslation(originalText, targetLanguage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { word ->
                dbRepository.insertWord(word)
            }.flatMap { _ ->
                loadHistorySingle()
            }
    }
}