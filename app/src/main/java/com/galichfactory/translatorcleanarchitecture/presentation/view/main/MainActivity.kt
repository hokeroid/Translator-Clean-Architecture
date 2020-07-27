package com.galichfactory.translatorcleanarchitecture.presentation.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galichfactory.translatorcleanarchitecture.R
import com.galichfactory.translatorcleanarchitecture.presentation.view.history.HistoryFragment
import com.galichfactory.translatorcleanarchitecture.presentation.view.translation.TranslationFragment

class MainActivity : AppCompatActivity(R.layout.activity_main), MainView {
    val translationFragment = TranslationFragment()
    val historyFragment = HistoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        //TranslatorApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainActivityFrameLayout, translationFragment)
            .add(R.id.mainActivityFrameLayout, historyFragment)
            .commit()

        showTranslationFragment()
    }

    override fun showTranslationFragment() {
        supportFragmentManager
            .beginTransaction()
            .hide(historyFragment)
            .show(translationFragment)
            .commit()
    }

    override fun showHistoryFragment() {
        supportFragmentManager
            .beginTransaction()
            .hide(translationFragment)
            .show(historyFragment)
            .commit()
    }
}