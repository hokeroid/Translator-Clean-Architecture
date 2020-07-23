package com.galichfactory.translatorcleanarchitecture.repository

import com.galichfactory.translatorcleanarchitecture.TranslatorApp
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
            "Bearer CggVAgAAABoBMxKABKqlfZxTRoRgNWAtkpGGQTbAan8dE8mbJjBQNhY1ntHLhcHS3pze_pv1nihF71zw8JT2JrxOsN9q_fbxnqUNGocXrb4nUy-TdijNirq9-uqGzKVTB0-WDNTn6KQHqHvj7DZ_ypNDCRumfWt-pPDz6q-VCcysGXUOO0aTqZaMpB5c4zOaPGnF2uqZ8JRMOEm5QmpLNPINFs6hKB7_Z_Ig4eJBsI-s0Dy1O8rd-p7aczDFNfSVGHmftSnNBD1dlnOgyjVeU3F_kFuNF38dUIJcCDHBtkFREevBuqoglZeDRtv2V1Y_ABU3Rfl6HyN6aqpj2aUY4bNGALBkGnNdzx1Jzl71HJkDGfnoG6cZPqdF_rrE6p3wqZd3qvW6ZSXu4GtACKEI9owaB_m13I09zA3gSXGEnsYhH8Ows2GW7H5WzEi8COMG1KUCcomY9ii1hmwu8cE8W141SU1Vw_4emF1ottZP50vrDXQZoOVg38RazE0bh3wdbyBXTUYTBCZGfx3Y1Oo4di5AlmVRvmt6aIad7rEAHb0zqrAWya-TScYPWqJUnO0kLmNzlrU5BNtkQvrDWS1sDPg1Jv7kuGSLMatp-ArTLLiqiuuUHHYcRe8j_ygzlAuK8DgHQYH-dQOmckInGqITOy9vfK6b1YoOIhCS8xNVGoZ-DDtPbRX85M5MuB3HGiQQk6jk-AUY0_nm-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="

        val result =
            translatorApiService.getTranslation(
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

    init {
        TranslatorApp.appComponent.inject(this)
    }
}