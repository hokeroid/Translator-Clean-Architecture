package com.galichfactory.translatorcleanarchitecture.domain

//data class Word(val wordName: String, val translations : List<Translation>)

data class Word(val originalText: String, val originalLanguage: String, val translatedText: String, val translatedLanguage: String)