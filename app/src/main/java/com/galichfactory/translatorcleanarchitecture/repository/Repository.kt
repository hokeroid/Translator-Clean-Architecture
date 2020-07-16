package com.galichfactory.translatorcleanarchitecture.repository

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.domain.Dictionary
import com.galichfactory.translatorcleanarchitecture.domain.Translation

interface Repository {
    fun getDictionary(context: Context) : Dictionary //TODO -Context +Dagger

    fun setDictionary(context: Context, dictionary: Dictionary) //TODO -Context +Dagger

    fun getTranslation(word: String, language: String) : Translation?
}