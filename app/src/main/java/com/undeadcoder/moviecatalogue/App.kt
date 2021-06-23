package com.undeadcoder.moviecatalogue

import android.app.Application
import com.undeadcoder.moviecatalogue.di.module.appModule
import com.undeadcoder.moviecatalogue.di.module.repoModule
import com.undeadcoder.moviecatalogue.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }

}