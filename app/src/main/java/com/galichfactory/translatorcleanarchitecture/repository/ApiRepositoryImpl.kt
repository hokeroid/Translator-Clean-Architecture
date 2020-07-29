package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.data.Result
import com.galichfactory.translatorcleanarchitecture.data.YandexTranslatorApiService
import com.galichfactory.translatorcleanarchitecture.data.YandexTranslatorQueryBody
import com.galichfactory.translatorcleanarchitecture.domain.Word
import io.reactivex.Single
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val translatorApiService: YandexTranslatorApiService) :
    ApiRepository {
    override fun getTranslation(originalText: String, targetLanguage: String): Single<Word> {
        val queryBody = YandexTranslatorQueryBody(
            folderId = "b1g8hrtq42kmn7g8a469",
            texts = listOf(originalText),
            targetLanguageCode = targetLanguage
        )
        val token =
            "Bearer CggVAgAAABoBMxKABKlkyzUCjXuYVpqaWGSCBujcg_gN3y79Jq5SZyl9f6gegvPbaay76ViBaSz1CnyHEByzw5iiUgCwwk4Qu4QLXWkCYdIPZNJumLnf2FIc1hGtFckpKlrU2s0ofjWVd2ygPS_KDTeJSHNPx32ogI3zBKv009DaBs_Sou7vEeDInzp1Vk4vsg6wPsgC6cTPwvJvotvdG6Slyn-uFUH3TJHmHorPc8EIb930Ivqg2LdvC8drhMr1lDRuTDJ4QQxcf5VwBBWCPqb4SY8cjLxhzWDTN4N4GCdhUMFuWVsIHTvOJ1A7qjGCJnzZL7CSn6pj4nYKU-h2SU5mb2rt0F1CBGyZl1CEbNjDdvKwI_DTxXZe5v_gxU6EA1e5NVOpoYnAnT638qOowMQzcLQcbtMmwVf0BKT4kFt1lssPDDaFdi9b8QJSu8Q78QSEnZmbrVqEu8iFEVONhkMJpAwuVvwL7SDyrSm5GzEb2XHn2IChiayIxV4H34DDM1hreR-k2owuolMkWKjCv4w1lc2-0TCZm-jDgTGwXyHEmBlQkBCoGnA5NVS_rXGpzJJYvw1xTGMj7vv1NegSgQ0H7-Z8zDNxGFZIaIzHs3v6MSfFp0BU28l_-LaD9_tTk8njqNe90SSXhVf_FswmAWej-MBVFtHOi5PiLE1O0PbFXmwXFgM5Y_eiS4LLGiQQiM39-AUYyJ6A-QUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="

        val result = translatorApiService.getTranslation(
            body = queryBody,
            headers = mapOf("Authorization" to token)
        )

        val resultToWord: (Result) -> Word =
            { r: Result ->
                Word(
                    originalText = originalText,
                    originalLanguage = r.translations[0].detectedLanguageCode,
                    translatedText = r.translations[0].text,
                    translatedLanguage = targetLanguage
                )
            }

        return result.map(resultToWord)
    }
}