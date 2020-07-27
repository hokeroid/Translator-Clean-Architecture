package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.data.AppDatabase
import com.galichfactory.translatorcleanarchitecture.data.DbWord
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Completable
import io.reactivex.Flowable
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

    override fun getWords(): Flowable<List<Word>> {
        return appDatabase.wordDao().getAll().map { dbWords: List<DbWord> ->
            dbWords.map { dbWord -> dbWord.toWord() }
        }
    }

    override fun insertWord(word: Word): Completable {
        return appDatabase.wordDao().insert(word.toDbWord())
    }
}