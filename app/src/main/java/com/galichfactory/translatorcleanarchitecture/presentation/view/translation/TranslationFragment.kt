package com.galichfactory.translatorcleanarchitecture.presentation.view.translation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.TranslationPresenter
import kotlinx.android.synthetic.main.fragment_translation.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class TranslationFragment : MvpAppCompatFragment(), TranslationView {

    @Inject
    lateinit var presenterProvider: Provider<TranslationPresenter>
    val presenter by moxyPresenter { presenterProvider.get() }

    override fun onAttach(context: Context) {
        TranslatorApp.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerAdapter = ArrayAdapter.createFromResource(
            view.context,
            R.array.languages,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        languageSpinner.adapter = spinnerAdapter

        translateButton.setOnClickListener {
            val text = searchField.editableText.toString()
            val lang = languageSpinner.selectedItem.toString()

            presenter.translateWord(text, lang)
        }

        historyButton.setOnClickListener {
            presenter.onHistoryButtonClick()
        }
    }

    override fun onTranslationStarted() {
        Toast.makeText(context, "Перевод начат", Toast.LENGTH_SHORT).show()
    }

    override fun onTranslationFinished(word: Word) {
        Toast.makeText(context, word.translatedText, Toast.LENGTH_SHORT).show()
    }

    override fun onTranslationError(error: Throwable) {
        Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}