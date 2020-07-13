package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.data.Dictionary
import com.galichfactory.translatorcleanarchitecture.data.Language
import com.galichfactory.translatorcleanarchitecture.data.Translation
import com.galichfactory.translatorcleanarchitecture.data.Word

interface IRepository {
    fun getDictionary() : Dictionary

    fun setDictionary()

    fun getTranslation(word: Word, language: Language) : Translation
}