package com.galichfactory.translatorcleanarchitecture.data

data class ResponceTranslation(val text: String, val detectedLanguageCode: String)

data class Result(val translations: List<ResponceTranslation>)