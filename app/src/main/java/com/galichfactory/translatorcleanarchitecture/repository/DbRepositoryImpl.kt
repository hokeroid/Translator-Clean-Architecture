package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.data.AppDatabase
import com.galichfactory.translatorcleanarchitecture.data.DbWord
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) : DbRepository {

    private fun DbWord.toWord(): Word {
        return Word(
            originalText = this.originalText,
            originalLanguage = this.originalLanguage,
            translatedText = this.translatedText,
            translatedLanguage = this.translatedLanguage
        )
    }

    private fun Word.toDbWord(): DbWord {
        return DbWord(
            originalText = this.originalText,
            originalLanguage = this.originalLanguage,
            translatedText = this.translatedText,
            translatedLanguage = this.translatedLanguage
        )
    }

    override fun getWords(): Single<List<Word>> {
        return appDatabase.wordDao().getAll().map { dbWords ->
            dbWords.map { dbWord ->
                dbWord.toWord()
            }
        }
    }

    override fun setWords(words: List<Word>) {
        appDatabase.clearAllTables()

        words.map { word ->
            appDatabase.wordDao().insert(word.toDbWord())
        }
    }

    override fun insertWord(word: Word) {
        appDatabase.wordDao().insert(word.toDbWord())
    }
}