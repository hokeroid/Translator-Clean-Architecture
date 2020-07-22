package com.galichfactory.translatorcleanarchitecture.presentation.view

import com.galichfactory.translatorcleanarchitecture.domain.Word
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {
    @AddToEndSingle
    fun showWords(words: List<Word>)
}