package com.egabruskiy.githubsearcher.network


import com.egabruskiy.githubsearcher.data.model.RepositoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GitRepoImpl(
    private val service: SearchService
    ): GitRepoRemote {



    override suspend fun getRepositories(query:String)
    : List<RepositoryResponse> = withContext(Dispatchers.IO) {

        val response = service.searchRepositories(query)
        response.repositories
    }
}