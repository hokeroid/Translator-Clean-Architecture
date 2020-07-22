package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.data.*
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single

class RepositoryImpl : Repository {

    override fun getWords(): List<Word> {
        val db = AppDatabase.create()

        return db.wordDao().getAll().map { dbWord: DbWord ->
            Word(
                originalText = dbWord.originalText,
                originalLanguage = dbWord.originalLanguage,
                translatedText = dbWord.translatedText,
                translatedLanguage = dbWord.translatedLanguage
            )
        }
    }

    override fun setWords(words: List<Word>) {
        val db = AppDatabase.create()

        db.clearAllTables()

        for (word in words) {
            val dbWord = DbWord(originalText = word.originalText,
                originalLanguage = word.originalLanguage,
                translatedText = word.translatedText,
                translatedLanguage = word.translatedLanguage)

            db.wordDao().insert(dbWord)
        }
    }

    override fun getTranslation(originalText: String, targetLanguage: String): Single<Word> {
        val queryBody = YandexTranslatorQueryBody(
            folderId = "b1g8hrtq42kmn7g8a469",
            texts = listOf(originalText),
            targetLanguageCode = targetLanguage
        )
        val token =
            "Bearer CggVAgAAABoBMxKABBR98dCLOb4VMyTN7oYe99tQ5lcgP-NGqv2sCI4nr_iWDoSxbvxfTdPE7y782-YPkuqAhiECrqR7CUMp9qgFwSWkBeR5esTkfYTdTyd-zOm9p7L4Pn9UyZ7WMtTsPEB7UMxyeV6NOCMU5gsHhbajb40sT1DSQY-rSCj6RXthzAO2rRTN21Sm455ccIIjiDg6cSimNqMtwBitn3dzhpNeIMjfA4IkVHNnssZ9Q7m5ClRBjyE1tgZjMIsS7C2lltPze7jUThWPORjW_tVofYFwiFpUshQtYXX35B2d9VGNdIPNJjqqPgjSqvlxdxh6vtJD-U_Vmg-qDTPQbkPRrzhZoeZ_ab3JD7p9aKtzzp3nq7BN9uV3F5UxQi_hU91Fhplj791VJVFZ9CCCA8TFLT4lWaZ7Agwm6ux4L6A9CDKDRCF7qwz6eggxM8gfdHTmLZzBdyAB1wCXi7YSxxXR2JJgv9pYDxh3EHU3xTRVPwuheAsSYipt436--i9tBKDuP2gRd4e0-InNNzjbtUkUMcWrrHTCr5bz7TTAKg3txulTA92iHgEpH-mb-a_tIgJS4xIav6uTk0hpdQKXcCr0LkWd-hewNV3XdChw5MwpDMymf20ZiBn5H_u8ocstdXloFsOfBpXwnZJwgFoWM0qRtragAFHjOCU9hXCAJDsK9Rqn6PpSGiQQ3-zR-AUYn77U-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="

        val result =
            YandexTranslatorApiService.create().getTranslation(
                body = queryBody,
                headers = mapOf("Authorization" to token)
            )

        val resultToWord: (Result) -> Word =
            { r: Result ->
                Word(originalText = originalText, originalLanguage = r.translations[0].detectedLanguageCode, translatedText = r.translations[0].text, translatedLanguage = targetLanguage)
            }

        return result.map(resultToWord)
    }
}