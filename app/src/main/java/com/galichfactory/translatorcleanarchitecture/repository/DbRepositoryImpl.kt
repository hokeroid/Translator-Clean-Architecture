package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.data.AppDatabase
import com.galichfactory.translatorcleanarchitecture.data.DbWord
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) : DbRepository {

    override fun getWords(): Single<List<Word>> {
        return appDatabase.wordDao().getAll().map { dbWords ->
            var words: MutableList<Word> = mutableListOf()
            for (dbWord in dbWords) {
                words.add(
                    Word(
                        originalText = dbWord.originalText,
                        originalLanguage = dbWord.originalLanguage,
                        translatedText = dbWord.translatedText,
                        translatedLanguage = dbWord.translatedLanguage
                    )
                )
            }
            words.toList()
        }
    }

    override fun setWords(words: List<Word>) {
        appDatabase.clearAllTables()

        for (word in words) {
            val dbWord = DbWord(
                originalText = word.originalText,
                originalLanguage = word.originalLanguage,
                translatedText = word.translatedText,
                translatedLanguage = word.translatedLanguage
            )

            appDatabase.wordDao().insert(dbWord)
        }
    }

    override fun insertWord(word: Word) {
        val dbWord = DbWord(
            originalText = word.originalText,
            originalLanguage = word.originalLanguage,
            translatedText = word.translatedText,
            translatedLanguage = word.translatedLanguage
        )

        appDatabase.wordDao().insert(dbWord)
    }

    init {
        TranslatorApp.appComponent.inject(this)
    }
}