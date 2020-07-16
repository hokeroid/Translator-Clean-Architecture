package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbWord(@PrimaryKey val wordName: String, @Embedded val translations : List<DbTranslation> = listOf()) {
    public constructor() : this("", listOf())
}