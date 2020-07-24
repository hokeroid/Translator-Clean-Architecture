package com.galichfactory.translatorcleanarchitecture.di

import com.galichfactory.translatorcleanarchitecture.presentation.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}