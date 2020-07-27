package com.galichfactory.translatorcleanarchitecture.presentation.view.main

import moxy.MvpView

interface MainView : MvpView {
    fun showTranslationFragment()

    fun showHistoryFragment()
}