package com.galichfactory.translatorcleanarchitecture.di

import com.galichfactory.translatorcleanarchitecture.presentation.view.history.HistoryFragment
import com.galichfactory.translatorcleanarchitecture.presentation.view.main.MainActivity
import com.galichfactory.translatorcleanarchitecture.presentation.view.translation.TranslationFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, BindsAppModule::class, NavigationModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(historyFragment: HistoryFragment)

    fun inject(translationFragment: TranslationFragment)
}