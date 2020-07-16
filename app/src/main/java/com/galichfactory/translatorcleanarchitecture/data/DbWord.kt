package com.galichfactory.translatorcleanarchitecture.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbWord(@PrimaryKey val word: String)