package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.domain.Word
import com.galichfactory.translatorcleanarchitecture.presentation.view.MainView
import com.galichfactory.translatorcleanarchitecture.repository.RepositoryImpl
import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {
    var words: MutableList<Word> = mutableListOf()
    val repository = RepositoryImpl()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        words = repository.getWords().toMutableList()

    }

    fun translateWord() {
        //TODO
    }
}