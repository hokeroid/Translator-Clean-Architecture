package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbTranslation(val language: String, @PrimaryKey val translation: String) {
    public constructor() : this("", "")
}