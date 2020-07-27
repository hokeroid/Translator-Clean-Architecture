package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.presentation.view.history.HistoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class HistoryPresenter @Inject constructor(private val interactor: Interactor) :
    MvpPresenter<HistoryView>() {

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
}