package com.galichfactory.translatorcleanarchitecture.presentation.view

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.repository.RepositoryImpl
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    lateinit var translateButton: Button
    lateinit var searchField: TextInputEditText
    lateinit var languageSpinner: Spinner
    lateinit var wordsRecyclerView: RecyclerView

    var words = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        translateButton = findViewById(R.id.translateButton)
        searchField = findViewById(R.id.searchField)
        languageSpinner = findViewById(R.id.languageSpinner)
        wordsRecyclerView = findViewById(R.id.wordsRecyclerView)

        translateButton.setOnClickListener {  } //TODO

        languageSpinner

        wordsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WordListAdapter(words)
        }

        testYandexApi()
        testYandexApi()
        testYandexApi()
        testYandexApi()
        testYandexApi()
        testYandexApi()
    }

    private fun testYandexApi() {
        val repositoryImpl = RepositoryImpl()
        repositoryImpl.getTranslation("Hello world", "ru")
        .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { word ->
                    Toast.makeText(applicationContext, "${word.originalText}: ${word.translatedText}", Toast.LENGTH_SHORT).show()
                    words.add(word)
                    wordsRecyclerView.adapter?.notifyDataSetChanged()
                },
                { error ->
                    error.printStackTrace()
                })
    }

    private fun testDb() {
        val repositoryImpl = RepositoryImpl()
        val dictionary = mutableListOf<Word>()
        val word = Word(
            originalText = "привет мир",
            originalLanguage = "ru",
            translatedText = "hello world",
            translatedLanguage = "en"
        )
        dictionary.add(word)
        repositoryImpl.setWords(context = applicationContext, words = dictionary)

        val newDictionary = repositoryImpl.getWords(context = applicationContext)
        Toast.makeText(applicationContext, newDictionary[0].translatedText, Toast.LENGTH_SHORT)
            .show()
    }
}