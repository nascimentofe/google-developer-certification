package com.nascimentofe.googledevelopercertification

import android.app.Application
import com.nascimentofe.googledevelopercertification.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }


        /**
         * Carregar os módulos
         */
        PresentationModule.load()
    }

}