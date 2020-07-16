package com.galichfactory.translatorcleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galichfactory.translatorcleanarchitecture.domain.Dictionary
import com.galichfactory.translatorcleanarchitecture.domain.Translation
import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.repository.RepositoryImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testDb()
    }

    fun testYandexApi() {
        val repositoryImpl = RepositoryImpl()
        repositoryImpl.getTranslation("Hello world", "ru")
    }

    fun testDb() {
        val repositoryImpl = RepositoryImpl()
        val dictionary = Dictionary(mutableListOf())
        val word = Word(
            wordName = "привет мир",
            translations = listOf(Translation(translation = "hello world", language = "en"))
        )
        dictionary.words.add(word)
        repositoryImpl.setDictionary(context = applicationContext, dictionary = dictionary)

        val newDictionary = repositoryImpl.getDictionary(context = applicationContext)
        print(newDictionary.words)
    }
}