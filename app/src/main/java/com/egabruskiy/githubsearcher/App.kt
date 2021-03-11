package com.egabruskiy.githubsearcher

import android.app.Application
import com.egabruskiy.githubsearcher.di.*
import com.egabruskiy.githubsearcher.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {


    override fun onCreate() {
        super.onCreate()
//        initToothpick()
//        initAppScope(Toothpick.openScope(DI.APP_SCOPE))



        setupKoin()
        setupTimber()
    }




    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                appModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }


    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }




//    fun initAppScope(appScope: Scope) {
//        appScope.installModules(
//            appModule(this)
//        )
//
//        val serverScope = Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE)
//        serverScope.installModules(
//            networkModule()
//        )
//
//    }
//    private fun initToothpick() {
//        if (BuildConfig.DEBUG) {
//            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
//        } else {
//            Toothpick.setConfiguration(Configuration.forProduction().preventMultipleRootScopes())
//        }
//    }
}