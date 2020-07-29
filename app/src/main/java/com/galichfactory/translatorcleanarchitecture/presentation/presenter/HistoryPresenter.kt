package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.presentation.view.history.HistoryView
import moxy.MvpPresenter

abstract class HistoryPresenter : MvpPresenter<HistoryView>() {
    abstract fun onBackButtonClick()
}