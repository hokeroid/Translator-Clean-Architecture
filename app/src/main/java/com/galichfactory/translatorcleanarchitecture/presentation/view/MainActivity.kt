package com.galichfactory.translatorcleanarchitecture.presentation.view

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
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
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class MainActivity : AppCompatActivity(R.layout.activity_main), MainView {
    lateinit var translateButton: Button
    lateinit var searchField: TextInputEditText
    lateinit var languageSpinner: Spinner
    lateinit var wordsRecyclerView: RecyclerView
    var words = listOf<Word>()

    //private val presenter by moxyPresenter { MainPresenter() }

    @Provides
    fun getContext(): Context {
        return applicationContext
    }


    override fun onCreate(savedInstanceState: Bundle?) {
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

//            repository.getTranslation(text, lang).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(
//                    { word ->
//                        Toast.makeText(applicationContext, "${word.originalText}: ${word.translatedText}", Toast.LENGTH_SHORT).show()
//                        presenter.words.add(word)
//                        wordsRecyclerView.adapter?.notifyDataSetChanged()
//                        repository.setWords(applicationContext, presenter.words)
//                    },
//                    { error ->
//                        error.printStackTrace()
//                    })
        }
    }

    private fun testYandexApi() {
        val repositoryImpl = RepositoryImpl()
        repositoryImpl.getTranslation("Hello world", "ru")
        .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { word ->
                    Toast.makeText(applicationContext, "${word.originalText}: ${word.translatedText}", Toast.LENGTH_SHORT).show()
                    //words.add(word)
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
        repositoryImpl.setWords(words = dictionary)

        val newDictionary = repositoryImpl.getWords()
        Toast.makeText(applicationContext, newDictionary[0].translatedText, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showWords(words: List<Word>) {
        this.words = words
        wordsRecyclerView.adapter?.notifyDataSetChanged()
    }
}