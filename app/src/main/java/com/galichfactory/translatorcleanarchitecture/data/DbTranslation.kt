package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbTranslation(@PrimaryKey val uid: Int, val language: String, val translation: String)