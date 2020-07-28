package com.galichfactory.translatorcleanarchitecture.presentation.view

import androidx.fragment.app.Fragment
import com.galichfactory.translatorcleanarchitecture.presentation.view.history.HistoryFragment
import com.galichfactory.translatorcleanarchitecture.presentation.view.translation.TranslationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


class TranslationScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return TranslationFragment()
    }
}

class HistoryScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return HistoryFragment()
    }
}