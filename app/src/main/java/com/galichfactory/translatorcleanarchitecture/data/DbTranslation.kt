package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = DbWord::class, parentColumns = ["word"], childColumns = ["parentWord"])])
data class DbTranslation(
    val language: String,
    @PrimaryKey val translation: String,
    val parentWord: String
)