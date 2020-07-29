package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.presentation.view.HistoryScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TranslationPresenterImpl @Inject constructor(
    private val interactor: Interactor,
    private val router: Router
) : TranslationPresenter() {
    private val compositeDisposable = CompositeDisposable()

    override fun translateWord(text: String, lang: String) {
        viewState.onTranslationStarted()
        compositeDisposable.add(
            interactor.getTranslation(text, lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ word ->
                    viewState.onTranslationFinished(word)
                }, { error ->
                    viewState.onTranslationError(error)
                })
        )
    }

    override fun onHistoryButtonClick() {
        router.navigateTo(HistoryScreen())
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}