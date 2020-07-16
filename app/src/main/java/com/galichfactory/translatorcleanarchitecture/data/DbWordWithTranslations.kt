package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Embedded
import androidx.room.Relation

data class DbWordWithTranslations(
    @Embedded val dbWord: DbWord,
    @Relation(
        parentColumn = "word",
        entity = DbTranslation::class,
        entityColumn = "parentWord"
    ) val dbTranslations: List<DbTranslation>
)