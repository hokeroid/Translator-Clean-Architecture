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
            "Bearer CggVAgAAABoBMxKABBM-yC2AmlT3waBNxcAruY-lT7CbsDJDAPBLZ1gE_n4vspFof6n23RQM-HYF451OQugaPGdUUWTxkkr9vyHw9PxB4wPff1VW1CZdCNdA6OpxGWoYonvdfQmLj1L3J6cxsVdko0GPRLr5gKWErfX_u0YqL_sEFoKmFWiwXABwtki9lU_RMRtUUdb_xXGcbmIwH1msLNxvmU6DNDVhoWiVXYi0yt6NuIXxC0uRi9pGmME16eETJUpDYMRUV4hPpkQofRA_T3LPo-55yqz1DY3Mah_BoRViNR3NtyXinL_Z9Kg4Tvl_sA5zfZw_f18uMq_0rpx8oKD58HVwnM6FpuWMm9c3hwwbJBGEluok4BWCCy6RmtWkwVPnfysNZRArj-UvTYJDPevWE85BnBfkLleOTw4xQg4lM4vIgggkXbVIUCRdu6onz-Q-_ZbzkRSg9zelW9n_xsKObAqiDVDikOi6PyCij2679ZG3DFiXj6b8QH9_6n7O9mN2Y5gZhup5KigoWOmwIf-vfOcaTJfKC2Z497kZEpovA2yni-_WOCcjDhw0oEaDEyuALxK0J5RWR6LBaeYaNBsRT0q_E_j7-CBkfK2chcHr-4BlOZicf153pjm0it2CMht8voKohH6j0oJApKlX5Xgk6P40YRvgy0q6LoovYTQDHTqe--vHjKzeTltiGiQQ1Jfr-AUYlOnt-AUiFgoUYWpldXJrN2NoaTFpNHNtNnNzdm8="

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