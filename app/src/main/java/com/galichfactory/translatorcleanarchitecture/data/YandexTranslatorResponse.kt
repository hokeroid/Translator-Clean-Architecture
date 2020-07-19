package com.galichfactory.translatorcleanarchitecture.data

data class ResponseTranslation(val text: String, val detectedLanguageCode: String)

data class Result(val translations: List<ResponseTranslation>)