//package com.egabruskiy.githubsearcher.diTooth
//
//import android.content.Context
//import com.egabruskiy.githubsearcher.rx.RxSchedulers
//import com.egabruskiy.githubsearcher.rx.SchedulersProvider
//import ru.terrakok.cicerone.Cicerone
//import ru.terrakok.cicerone.NavigatorHolder
//import ru.terrakok.cicerone.Router
//import toothpick.ktp.binding.module
//
//fun appModule(context: Context) = module {
//    //Global
//    bind(Context::class.java).toInstance(context)
//
//    // Navigation
//    val cicerone = Cicerone.create()
//    bind(Router::class.java).toInstance(cicerone.router)
//    bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
//
//    bind(SchedulersProvider::class.java).toInstance(RxSchedulers())
//}
//
