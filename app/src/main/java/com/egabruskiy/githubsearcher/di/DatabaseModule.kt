package com.egabruskiy.githubsearcher.di

import android.app.Application
import androidx.room.Room
import com.egabruskiy.githubsearcher.data.database.AppDataBase
import com.egabruskiy.githubsearcher.data.database.GitRepoDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "githubsearcher_db"

val databaseModule = module {


    single { provideAppDatabase(androidApplication()) }

    single { provideGitRepoDao(get()) }

}

private fun provideAppDatabase(application: Application): AppDataBase {
    return Room.databaseBuilder(application, AppDataBase::class.java, DATABASE_NAME)
        .build()
}

private fun provideGitRepoDao(database: AppDataBase): GitRepoDao {
    return database.gitRepoDao()
}
