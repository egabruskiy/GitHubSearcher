package com.egabruskiy.githubsearcher.network

import com.egabruskiy.githubsearcher.data.model.RepositoryResponse

interface GitRepoRemote {

    suspend fun getRepositories(query:String): List<RepositoryResponse>

}