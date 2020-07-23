package com.galichfactory.translatorcleanarchitecture.di

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.data.AppDatabase
import com.galichfactory.translatorcleanarchitecture.data.YandexTranslatorApiService
import com.galichfactory.translatorcleanarchitecture.interactors.Interactor
import com.galichfactory.translatorcleanarchitecture.interactors.InteractorImpl
import com.galichfactory.translatorcleanarchitecture.presentation.presenter.MainPresenter
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepository
import com.galichfactory.translatorcleanarchitecture.repository.ApiRepositoryImpl
import com.galichfactory.translatorcleanarchitecture.repository.DbRepository
import com.galichfactory.translatorcleanarchitecture.repository.DbRepositoryImpl
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

    @Provides
    @Singleton
    internal fun provideDbRepository(appDatabase: AppDatabase): DbRepository {
        return DbRepositoryImpl(appDatabase)
    }

    @Provides
    @Singleton
    internal fun provideApiRepository(translatorApiService: YandexTranslatorApiService): ApiRepository {
        return ApiRepositoryImpl(translatorApiService)
    }

    @Provides
    @Singleton
    internal fun provideInteractor(
        dbRepository: DbRepository,
        apiRepository: ApiRepository
    ): Interactor {
        return InteractorImpl(dbRepository, apiRepository)
    }

    @Provides
    @Singleton
    internal fun provideMainPresenter(interactor: Interactor): MainPresenter {
        return MainPresenter(interactor)
    }
}