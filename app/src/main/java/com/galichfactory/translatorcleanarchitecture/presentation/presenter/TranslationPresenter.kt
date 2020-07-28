package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.presentation.view.translation.TranslationView
import moxy.MvpPresenter

abstract class TranslationPresenter :
    MvpPresenter<TranslationView>() {
    abstract fun onHistoryButtonClick()

    abstract fun translateWord(text: String, lang: String)
}