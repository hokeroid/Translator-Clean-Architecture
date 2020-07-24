package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.presentation.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val interactor: Interactor) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        subscribeOnWordHistory()
    }

    private fun subscribeOnWordHistory() {
        interactor.getHistoryObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ words ->
                viewState.showWords(words)
            },
                { error -> error.printStackTrace() })
    }

    fun translateWord(text: String, lang: String) {
        interactor.getTranslation(text, lang)
    }
}