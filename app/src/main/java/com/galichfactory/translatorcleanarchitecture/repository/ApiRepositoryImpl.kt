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
            "Bearer CggVAgAAABoBMxKABLuNHIbjE2GqdGjyhQb5dEiGd16SFHMWz3ougJOu6MfR_c8JAUu6t93VnqxXNzFRgWXe2gks5WiUFgnCMnTHxwY3E2Wv1NhQ73h3yakNOh9aQRrp9eGQWojcL5Qcg4ldDWR9SSj4mseVm2fRck7TeRGV-y5vo5RAvHqV6fi4tmybUV_eacfDJAlsO1rD0OF3HiZCOvxunD82QW2cnYcbdWaZOECezEA5l05ltMyS-rlbMyna1D7o47XvKIgoa0CIey7E8Sif-Vqr0mfOCYhSbY4lGvkGwEDf4qNHklEZyUNzzHJsi10vZtk6LN_MdVVodFehiLclGp0U3dZs3HsTkYLIGhRsacoG5-E6l0szoBgJJXm7KIcpLbUFMH6kUHMHg783Dg4J3BXkQp2h-JHu5LbxEgbuh6KfGpDjHITPr-s3XgU6f9YgNtu6lKilrDEOpQ5F1EYxSWquqHpUOTmILTtyalrSeE2Ey183UjRH24ZgChUVc9gq-96gW9CXPIzz1SrIvNBJi5YGaaU4sA254R5eREW_wkMM33LtfHCMExjEpC0aZhF3zzkJAVFxfWVm5AEUPw-0CUqa8M0lsGVuKpyxgAS2sp-ljKt-ZnnCkUBvJVGO1dU_WhDgt2DrVkYBCKqGR4fXE4kdWRnYPBbAU-599JcibpFiky6b18Np4xlSGiQQxKL4-AUYhPT6-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="
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