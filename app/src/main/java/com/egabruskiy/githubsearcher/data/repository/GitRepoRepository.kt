package com.egabruskiy.githubsearcher.data.repository

import com.egabruskiy.githubsearcher.data.database.GitRepoDao
import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse
import com.egabruskiy.githubsearcher.data.model.ResourceResult
import com.egabruskiy.githubsearcher.network.GitRepoRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber

class GitRepoRepository(
    private val remote: GitRepoRemote,
    private val dao: GitRepoDao
) {


    suspend fun saveRepository( gitRepoModel: GitRepoModel){
        dao.insertRepo(gitRepoModel)
    }

    fun removeRepository( gitRepoModel: GitRepoModel){

    }


//     suspend fun getRepositories(query:String)
//            : List<RepositoryResponse> = withContext(Dispatchers.IO) {
//
//        val response = service.searchRepositories(query)
//        response.repositories
//    }

    fun repoById(id:String):Flow<ResourceResult<GitRepoModel>> = flow {
        emit(getRepoById(id))
    }


    suspend  fun getRepoById(id:String)= withContext(Dispatchers.IO) {
        ResourceResult.successOrNull(dao.getRepoById(id))
    }






    val savedRepositories:Flow<ResourceResult<List<GitRepoModel>>>
    get() = dao.getAllRepos().map { repos->
        ResourceResult.successOrEmpty(repos)
    }

    fun getReposByQuery(query:String): Flow<ResourceResult<List<RepositoryResponse>>> = flow{

        emit(ResourceResult.loading())

        val repos = remote.getRepositories(query)

        emit(ResourceResult.successOrEmpty(repos))
    }.catch { e ->
        emit(ResourceResult.error(e))
    }

}
