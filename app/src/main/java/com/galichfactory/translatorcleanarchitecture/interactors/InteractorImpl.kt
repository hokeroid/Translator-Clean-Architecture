package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepository
import com.galichfactory.translatorcleanarchitecture.repository.DbRepository
import io.reactivex.Single
import okhttp3.internal.wait
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
        apiRepository.getTranslation(originalText, targetLanguage)
            .subscribe({ word ->
                dbRepository.insertWord(word).wait()
            }, { error -> error.printStackTrace() }).dispose()
        return loadHistorySingle()
    }
}