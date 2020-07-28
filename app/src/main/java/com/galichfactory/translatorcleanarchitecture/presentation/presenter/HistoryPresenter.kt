package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import android.util.Log
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.presentation.view.history.HistoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class HistoryPresenter @Inject constructor(private val interactor: Interactor) :
    MvpPresenter<HistoryView>() {
    val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        Log.d("historyPresenter", "History Presenter onFirstViewAttach()")
        subscribeOnWordHistory()
    }

    private fun subscribeOnWordHistory() {
        compositeDisposable.add(
            interactor.getHistoryObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ words ->
                    viewState.showWords(words)
                },
                    { error -> error.printStackTrace() })
        )
    }

    fun onBackButtonClick() {
        TranslatorApp.cicerone.router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("historyPresenter", "History Presenter onDestroy()")

        compositeDisposable.dispose()
    }
}