package com.galichfactory.translatorcleanarchitecture.repository

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.data.*
import com.galichfactory.translatorcleanarchitecture.domain.Dictionary
import com.galichfactory.translatorcleanarchitecture.domain.Translation
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

class RepositoryImpl : Repository {
    private fun mapDictionaryToDbWords(dictionary: Dictionary): List<DbWordWithTranslations> {
        val dbWordsWithTranslations: MutableList<DbWordWithTranslations> = mutableListOf()
        for (word in dictionary.words) {
            val dbTranslations: MutableList<DbTranslation> = mutableListOf()
            for (translation in word.translations) {
                dbTranslations.add(
                    DbTranslation(
                        language = translation.language,
                        translation = translation.translation,
                        parentWord = word.wordName
                    )
                )
            }

            val dbWordWithTranslations =
                DbWordWithTranslations(
                    dbTranslations = dbTranslations,
                    dbWord = DbWord(word = word.wordName)
                )
            dbWordsWithTranslations.add(dbWordWithTranslations)
        }
        return dbWordsWithTranslations
    }

    private fun mapDbWordsToDictionary(dbWordsWithTranslations: List<DbWordWithTranslations>): Dictionary {
        val dictionary = Dictionary(mutableListOf())

        for (dbWordWithTranslation in dbWordsWithTranslations) {
            val translations: MutableList<Translation> = mutableListOf()
            for (dbTranslation in dbWordWithTranslation.dbTranslations) {
                translations.add(
                    Translation(
                        language = dbTranslation.language,
                        translation = dbTranslation.translation
                    )
                )
            }

            val word =
                Word(translations = translations, wordName = dbWordWithTranslation.dbWord.word)
            dictionary.words.add(word)
        }
        return dictionary
    }

    override fun getDictionary(context: Context): Dictionary {
        val db = AppDatabase.create(context)

        return mapDbWordsToDictionary(db.wordDao().getAll())
    }

    override fun setDictionary(context: Context, dictionary: Dictionary) {
        val db = AppDatabase.create(context)

        db.clearAllTables() //TODO erase this when stop testing

        val dbWords = mapDictionaryToDbWords(dictionary)
        for (dbWord in dbWords) {
            db.wordDao().insert(dbWord.dbWord)
            for (dbTranslation in dbWord.dbTranslations)
                db.wordDao().insert(dbTranslation)
        }
    }

    override fun getTranslation(word: String, language: String): Single<Translation> {
        val queryBody = YandexTranslatorQueryBody(
            folderId = "b1g8hrtq42kmn7g8a469",
            texts = listOf(word),
            targetLanguageCode = language
        )
        val token =
            "Bearer CggVAgAAABoBMxKABKrvMe05JSRLGcRqdazoLR7PGumUusw5WOOcF6rYXnn-TzoC39aJas9LibNd0H5Pa5C0jtPgmjh1JP-rR8qz7dXJGyNxuF8zTHhxBoMFKIbwKxGyvMs2Llgo1wSXrUdb2oGesrmvStVV9gSqfn85cD63DBF_9rpWThyb3GNvk4JDDSfGWyreqJbwHIgo3FXJokXJV_yljXi9OZS6UpOiF-Qrz3fp4eFD57mxJnFjQyM9s92aNfKjPRNB7ihm0ScoQLr337Nk3bkk25DL8U0otUOv_Bz56777nG3xVte7ObYeaVCOvmQw8Dkm2mR0J4yBmI-hbXfHoXteT_WXBBgfVggQEV5wqRh3aALMhEyXx_MTNj_hqWkBc9UJkRH0hwmNhz7lFRUKnRIZk-avs5pPzoH5oT3aAOhoYof174LDaboj-Qe3DzCCK__pV37NYPNWhbgezTsXrWeE55bddfvTqrr0ReLnHmXYqr8N1RTerwHsfRHFOrU6gQaFZCsPiTiIfnpwiBZHyvZAAmlw6Z8-aTeAq6wtPeoQjowZQsMA3E0rHEOhxWQH6-M6TgmLxGniLfROv9hyDnZqbEF_bS28NR14NpSX9n3HNkz5zmQDUM73NXYR-25VPQivVXI0R_65SQ2sdu44gPmMdjLRZqUwosXIButlDTzqLbfzglgubesZGiQQ0O3I-AUYkL_L-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="

        val result =
            YandexTranslatorApiService.create().getTranslation(
                body = queryBody,
                headers = mapOf("Authorization" to token)
            )

//        result.observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({ result ->
//                print("result is ${result.translations[0].text}")
//                val language = result.translations[0].detectedLanguageCode
//                val text = result.translations[0].text
//                translation = Translation(language = language, translation = text)
//            },
//                { error ->
//                    error.printStackTrace()
//                })
        val resultToTranslation: (Result) -> Translation =
            {
                r: Result -> Translation(translation = r.translations[0].text, language = language)
            }
        return result.map(resultToTranslation)
    }
}