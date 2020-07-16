package com.galichfactory.translatorcleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.data.YandexTranslatorApiService
import com.galichfactory.translatorcleanarchitecture.repository.RepositoryImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testRepository()
    }

    fun testRepository() {
        val repositoryImpl = RepositoryImpl(translatorApiService = YandexTranslatorApiService.create())
        repositoryImpl.getTranslation("Hello world", "ru")
    }
}