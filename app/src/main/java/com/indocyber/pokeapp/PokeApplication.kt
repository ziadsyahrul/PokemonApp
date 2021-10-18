package com.indocyber.pokeapp

import android.app.Application
import com.indocyber.pokeapp.di.appModule
import com.indocyber.pokeapp.di.uiModule
import com.indocyber.pokeapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class PokeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@PokeApplication)
            modules(listOf(appModule, uiModule, useCaseModule))
        }
    }
}