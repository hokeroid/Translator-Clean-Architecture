package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.data.Dictionary
import com.galichfactory.translatorcleanarchitecture.data.Language
import com.galichfactory.translatorcleanarchitecture.data.Translation
import com.galichfactory.translatorcleanarchitecture.data.Word

class Repository : IRepository {
    override fun getDictionary(): Dictionary {
        TODO("Not yet implemented")
    }

    override fun setDictionary() {
        TODO("Not yet implemented")
    }

    override fun getTranslation(word: Word, language: Language): Translation {
        TODO("Not yet implemented")
    }

}