package com.galichfactory.translatorcleanarchitecture.data

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface YandexTranslatorApiService {
    @POST("translate/v2/translate")
    fun getTranslation(
        @Body body: YandexTranslatorQueryBody,
        @HeaderMap headers: Map<String, String>
    ): Observable<Result>

    companion object Factory {
        fun create(): YandexTranslatorApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://translate.api.cloud.yandex.net/")
                .build()

            return retrofit.create(YandexTranslatorApiService::class.java)
        }
    }
}