package com.galichfactory.translatorcleanarchitecture

import android.app.Application
import com.galichfactory.translatorcleanarchitecture.di.AppComponent
import com.galichfactory.translatorcleanarchitecture.di.AppModule
import com.galichfactory.translatorcleanarchitecture.di.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Singleton
class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent

        val cicerone = Cicerone.create()
    }
}