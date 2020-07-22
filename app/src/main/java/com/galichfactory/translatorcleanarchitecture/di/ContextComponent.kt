package com.galichfactory.translatorcleanarchitecture.di

import android.content.Context
import com.galichfactory.translatorcleanarchitecture.presentation.view.MainActivity
import dagger.Component

@Component(modules = [MainActivity::class])
interface ContextComponent {
    fun getContext(): Context
}