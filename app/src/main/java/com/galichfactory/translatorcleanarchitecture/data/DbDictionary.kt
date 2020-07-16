package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Entity

@Entity
data class DbDictionary(val words: List<DbWord>)