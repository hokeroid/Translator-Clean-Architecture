package com.galichfactory.translatorcleanarchitecture.di

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.data.AppDatabase
import com.galichfactory.translatorcleanarchitecture.data.YandexTranslatorApiService
import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.MainPresenter
import com.galichfactory.translatorcleanarchitecture.presentation.view.MainActivity
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepository
import com.galichfactory.translatorcleanarchitecture.repository.DbRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(context: Context)

    fun inject(dbRepository: DbRepository)

    fun inject(apiRepository: ApiRepository)

    fun inject(appDatabase: AppDatabase)

    fun inject(translatorApiService: YandexTranslatorApiService)

    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)

    //fun inject(mainPresenterProvider: Provider<MainPresenter>)

    fun inject(interactor: Interactor)
}