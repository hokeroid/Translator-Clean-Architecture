package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Entity

@Entity(primaryKeys = ["originalText", "translatedLanguage"])
data class DbWord(val originalText: String, val originalLanguage: String, val translatedText: String, val translatedLanguage: String)