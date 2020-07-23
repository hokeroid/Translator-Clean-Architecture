package com.galichfactory.translatorcleanarchitecture.presentation.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.MainPresenter
import com.google.android.material.textfield.TextInputEditText
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {
    lateinit var translateButton: Button
    lateinit var searchField: TextInputEditText
    lateinit var languageSpinner: Spinner
    lateinit var wordsRecyclerView: RecyclerView
    var words = listOf<Word>()

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        TranslatorApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        translateButton = findViewById(R.id.translateButton)
        searchField = findViewById(R.id.searchField)
        languageSpinner = findViewById(R.id.languageSpinner)
        wordsRecyclerView = findViewById(R.id.wordsRecyclerView)

        val spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        languageSpinner.adapter = spinnerAdapter

        wordsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WordListAdapter(words)
        }

        translateButton.setOnClickListener {
            val text = searchField.editableText.toString()
            val lang = languageSpinner.selectedItem.toString()

            presenter.translateWord(text, lang)
        }
    }

//    private fun testYandexApi() {
//        apiRepository.getTranslation("Hello world", "ru")
//        .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                { word ->
//                    Toast.makeText(applicationContext, "${word.originalText}: ${word.translatedText}", Toast.LENGTH_SHORT).show()
//                    //words.add(word)
//                    wordsRecyclerView.adapter?.notifyDataSetChanged()
//                },
//                { error ->
//                    error.printStackTrace()
//                })
//    }
//
//    private fun testDb() {
//        val dictionary = mutableListOf<Word>()
//        val word = Word(
//            originalText = "привет мир",
//            originalLanguage = "ru",
//            translatedText = "hello world",
//            translatedLanguage = "en"
//        )
//        dictionary.add(word)
//        dbRepository.setWords(words = dictionary)
//
//        val newDictionary = repositoryImpl.getWords()
//        Toast.makeText(applicationContext, newDictionary[0].translatedText, Toast.LENGTH_SHORT)
//            .show()
//    }

    override fun showWords(words: List<Word>) {
        this.words = words
        wordsRecyclerView.adapter?.notifyDataSetChanged()
    }
}