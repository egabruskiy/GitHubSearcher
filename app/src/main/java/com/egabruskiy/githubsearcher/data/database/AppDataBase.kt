package com.egabruskiy.githubsearcher.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.egabruskiy.githubsearcher.data.model.GitRepoModel


@Database(entities = [GitRepoModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gitRepoDao(): GitRepoDao
}