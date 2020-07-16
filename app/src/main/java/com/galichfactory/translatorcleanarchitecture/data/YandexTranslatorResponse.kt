package com.galichfactory.translatorcleanarchitecture.data

data class Translation(val text: String, val detectedLanguageCode: String)

data class Result(val translations: List<Translation>)