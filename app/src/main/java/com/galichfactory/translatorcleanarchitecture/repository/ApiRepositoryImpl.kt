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
            "Bearer CggVAgAAABoBMxKABComVMW1tEY_Uoswxlm9QH2HwuredIkgBYtjh00-aZHrG3aAoB04Rv5vTvAi_u9c-Cklmg-tCc4mNa3i3Kccu1MFEGNaBlSXxQY4co9zdOJSE3Pw2p1y2oz_7P7rxthc1jsEE_41pnwKT_EdlUuGRs1X_hoMCUWagxl5hDpQ1tffTBIqO5mcyXof9-cOtZIfmYVesAe9GjtQziqg9mV-nA3d3NcLTowk3hTczIWMk2w1MifXgAxxo0bPkOA1oK0mq-1poknCOjnIIBIesMwA698is8rExVifgGcE7GVOpfxMeQRmwk0Wv-pxjROOj_wGcMu5NKBthS-jcbeQzX0DPAJZ1_NO4fRk_tDmnTmVcOUqIkD7uOkArh3khmMwRt2Sw6L1MVKSDziWW1AtKd66YLK5KPoUiQQNMh8YbAMWL0zybn8RfAY_rZ3Eb82iaNVh1L0t436EZlUQuXkHoBBKU-53MXP7QFHD1b8bi1PDupKL_VBW6zwDzNb6gEy0sLnbhI1a352Lm-FNg3GEo8C-eS-RGT39gAyaNKCnKwLB_JkfuNaJ9ef6ZDGFNm8GwM0Rkqi9MM2r4eg5tHptrS553mlSIxkW5Z28aH_3zifV6q39EK6U87wN_ke8vnDhdXkzzaXCODIOik5SnwNNSygXnjr8ZpRXBuu7gj7pyJ0_s4dRGiQQ46no-AUYo_vq-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="
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
}