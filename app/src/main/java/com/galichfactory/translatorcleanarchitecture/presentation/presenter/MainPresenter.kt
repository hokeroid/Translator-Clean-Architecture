package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.presentation.view.MainView
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val interactor: Interactor) :
    MvpPresenter<MainView>() {

    //var words: MutableList<Word> = mutableListOf() //Так же эффективнее?

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadHistory()
    }

    fun loadHistory() {
        interactor.loadHistorySingle()
            .subscribeOn(Schedulers.io())
            .subscribe({ words ->
                viewState.showWords(words)
            },
                { error -> error.printStackTrace() }).dispose()
    }

    fun translateWord(text: String, lang: String) {
        interactor.getTranslation(text, lang)
            .subscribe(
                { words ->
                    viewState.showWords(words)
                },
                { error ->
                    error.printStackTrace()
                }).dispose()
    }
}