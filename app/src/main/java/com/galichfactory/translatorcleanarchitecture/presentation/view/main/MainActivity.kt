package com.galichfactory.translatorcleanarchitecture.presentation.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.presentation.view.TranslationScreen
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TranslatorApp.cicerone.router.navigateTo(TranslationScreen())
    }

    override fun onResume() {
        super.onResume()
        TranslatorApp.cicerone.navigatorHolder.setNavigator(
            SupportAppNavigator(
                this,
                R.id.mainActivityFrameLayout
            )
        )
    }

    override fun onPause() {
        super.onPause()
        TranslatorApp.cicerone.navigatorHolder.removeNavigator()
    }
}