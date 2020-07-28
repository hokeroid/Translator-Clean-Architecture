package com.galichfactory.translatorcleanarchitecture.presentation.presenter

import android.util.Log
import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class HistoryPresenterImpl @Inject constructor(
    private val interactor: Interactor,
    private val router: Router
) : HistoryPresenter() {
    private val compositeDisposable = CompositeDisposable()

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

    override fun onBackButtonClick() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("historyPresenter", "History Presenter onDestroy()")

        compositeDisposable.dispose()
    }
}