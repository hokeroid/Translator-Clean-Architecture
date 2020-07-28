package com.galichfactory.translatorcleanarchitecture.di

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.data.AppDatabase
import com.galichfactory.translatorcleanarchitecture.data.YandexTranslatorApiService
import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.interactors.InteractorImpl
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.HistoryPresenter
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.HistoryPresenterImpl
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.TranslationPresenter
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.TranslationPresenterImpl
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepository
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepositoryImpl
import com.galichfactory.translatorcleanarchitecture.repository.DbRepository
import com.galichfactory.translatorcleanarchitecture.repository.DbRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val translatorApp: TranslatorApp) {
    @Provides
    internal fun provideApplication(): TranslatorApp {
        return translatorApp
    }

    @Provides
    internal fun provideContext(): Context {
        return translatorApp.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.create(context)
    }

    @Provides
    @Singleton
    internal fun provideApiService(): YandexTranslatorApiService {
        return YandexTranslatorApiService.create()
    }
}

@Module
interface BindsAppModule {
    @Binds
    @Singleton
    fun provideDbRepository(dbRepository: DbRepositoryImpl): DbRepository

    @Binds
    @Singleton
    fun provideApiRepository(apiRepository: ApiRepositoryImpl): ApiRepository

    @Binds
    @Singleton
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    fun provideHistoryPresenter(historyPresenter: HistoryPresenterImpl): HistoryPresenter

    @Binds
    fun provideTranslationPresenter(translationPresenter: TranslationPresenterImpl): TranslationPresenter
}