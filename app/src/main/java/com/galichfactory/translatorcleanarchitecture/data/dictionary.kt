package com.galichfactory.translatorcleanarchitecture.data

import com.galichfactory.translatorcleanarchitecture.data.Word

enum class Language {
    RUSSIAN,
    ENGLISH,
    GERMAN,
    FRENCH
}

class Dictionary {
    var words: List<Word> = listOf()
}