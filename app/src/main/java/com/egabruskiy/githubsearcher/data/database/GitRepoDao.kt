package com.egabruskiy.githubsearcher.data.database

import androidx.room.*
import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import kotlinx.coroutines.flow.Flow


@Dao
interface GitRepoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo( repoModel: GitRepoModel)



    @Query("SELECT * FROM gitmodel WHERE id = :id")
     fun getRepoById( id:String): GitRepoModel


    @Query("SELECT * FROM gitmodel ")
    fun getAllRepos(): Flow<List<GitRepoModel>>


}