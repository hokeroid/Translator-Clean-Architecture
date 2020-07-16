package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbWord(@PrimaryKey val wordName: String, val translations : List<DbTranslation>)