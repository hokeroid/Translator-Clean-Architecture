package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.presentation.view.HistoryScreen
import com.galichfactory.translatorcleanarchitecture.presentation.view.translation.TranslationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class TranslationPresenter @Inject constructor(private val interactor: Interactor) :
    MvpPresenter<TranslationView>() {
    private val compositeDisposable = CompositeDisposable()

    fun translateWord(text: String, lang: String) {
        viewState.onTranslationStarted()
        compositeDisposable.add(interactor.getTranslation(text, lang)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ word ->
                viewState.onTranslationFinished(word)
            }, { error ->
                viewState.onTranslationError(error)
            })
        )
    }

    fun onHistoryButtonClick() {
        TranslatorApp.cicerone.router.navigateTo(HistoryScreen())
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}