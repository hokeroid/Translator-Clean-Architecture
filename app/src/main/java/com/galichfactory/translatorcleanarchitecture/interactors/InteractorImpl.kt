package com.galichfactory.translatorcleanarchitecture.interactors

import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.repository.RepositoryImpl
import io.reactivex.Single

class InteractorImpl: Interactor {
    val repository = RepositoryImpl()
    override fun saveDictionary(words: List<Word>) {
        TODO("Not yet implemented")
    }

    override fun loadDictionary(): List<Word> {
        TODO("Not yet implemented")
    }

    override fun getTranslation(originalText: String, targetLanguage: String): Single<Word> {
        return repository.getTranslation(originalText, targetLanguage)
    }
}