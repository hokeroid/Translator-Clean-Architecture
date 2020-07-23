package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

interface ApiRepository {
    fun getTranslation(originalText: String, targetLanguage: String): Single<Word>
}