package com.galichfactory.translatorcleanarchitecture

import android.app.Application
import com.galichfactory.translatorcleanarchitecture.di.AppComponent
import com.galichfactory.translatorcleanarchitecture.di.AppModule
import com.galichfactory.translatorcleanarchitecture.di.DaggerAppComponent

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        @JvmStatic //TODO осознать что это
        lateinit var appComponent: AppComponent
    }
}