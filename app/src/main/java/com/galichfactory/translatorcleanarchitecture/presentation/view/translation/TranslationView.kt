package com.galichfactory.translatorcleanarchitecture.presentation.view.translation

import com.galichfactory.translatorcleanarchitecture.domain.Word
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface TranslationView : MvpView {
    @SingleState
    fun onTranslationStarted()

    @SingleState
    fun onTranslationFinished(word: Word)

    @SingleState
    fun onTranslationError(error: Throwable)
}