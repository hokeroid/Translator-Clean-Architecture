package com.galichfactory.translatorcleanarchitecture.presentation.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.TranslatorApp
import com.galichfactory.translatorcleanarchitecture.presentation.view.TranslationScreen
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        TranslatorApp.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        router.navigateTo(TranslationScreen())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(
            SupportAppNavigator(
                this,
                R.id.mainActivityFrameLayout
            )
        )
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}