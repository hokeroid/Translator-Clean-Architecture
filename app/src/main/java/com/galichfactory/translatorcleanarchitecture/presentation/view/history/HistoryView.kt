package com.galichfactory.translatorcleanarchitecture.presentation.view.history

import com.galichfactory.translatorcleanarchitecture.domain.Word
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface HistoryView : MvpView {
    @AddToEndSingle
    fun showWords(words: List<Word>)
}