package com.nascimentofe.googledevelopercertification.presentation.di

import com.nascimentofe.googledevelopercertification.presentation.ui.main.MainViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            factory {
                MainViewModel()
            }
        }
    }

}