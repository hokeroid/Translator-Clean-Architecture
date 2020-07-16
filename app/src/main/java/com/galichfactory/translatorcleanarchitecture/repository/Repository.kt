package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.domain.Dictionary
import com.galichfactory.translatorcleanarchitecture.domain.Translation

interface Repository {
    fun getDictionary() : Dictionary

    fun setDictionary()

    fun getTranslation(word: String, language: String) : Translation?
}